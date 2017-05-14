package com.framgia.capstone.ui.datlich;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.LichKham;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.utils.RestAPI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadPhongKham;
import static com.framgia.capstone.utils.SharedPreferencesUtils.loadUser;

/**
 * Created by tri on 30/04/2017.
 */

public class LichTrongFragment extends Fragment
        implements LichTrongAdapter.ItemClickListener, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private LichTrongAdapter mAdapter;
    private List<LichKham> mList = new ArrayList<>();
    private LichKham mLichKham;

    private TextView mChonNgay;
    private TextView mMgay;
    private TextView mAll;
    private TextView mKhongLich;

    private Calendar mCalendar;
    private PhongKham mPhongKham;
    private String mUser;

    private PendingIntent mPendingIntent;

    SimpleDateFormat mFormat = new SimpleDateFormat("dd-MM-yyyy");

    public LichTrongFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lichtrong, container, false);

        mChonNgay = (TextView) view.findViewById(R.id.text_chonNgay);
        mMgay = (TextView) view.findViewById(R.id.text_ngay);
        mAll = (TextView) view.findViewById(R.id.text_all);
        mKhongLich = (TextView) view.findViewById(R.id.textKhongLich);

        mCalendar = Calendar.getInstance();

        mPhongKham = loadPhongKham(getActivity());
        mUser = loadUser(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_lichtrong);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LichTrongAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);

        mChonNgay.setOnClickListener(this);
        mAll.setOnClickListener(this);

        new AsynListLich().execute();

        return view;
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onDangKy(LichKham lichKham) {
        new AsynDatLich().execute(lichKham);
        mMgay.setText("");
        nhacLK(lichKham);
    }

    public void nhacLK(LichKham lichKham) {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(getActivity(), AlarmReceiverDL.class);
        mPendingIntent =
                PendingIntent.getBroadcast(getActivity(), Integer.parseInt(lichKham.getMa()),
                        alarmIntent, 0);

        int gio = Integer.parseInt(lichKham.getTgBatDau().substring(0, 2));
        int phut = Integer.parseInt(lichKham.getTgBatDau().substring(3, 5));
        int ngay = Integer.parseInt(lichKham.getNgay().substring(0, 2));
        int thang = Integer.parseInt(lichKham.getNgay().substring(3, 5));
        int nam = Integer.parseInt(lichKham.getNgay().substring(6, 10));

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(Calendar.HOUR_OF_DAY, gio - 1);
        calendar.set(Calendar.MINUTE, phut);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
        //   calendar.set(Calendar.MONTH, thang);
        calendar.set(Calendar.DAY_OF_MONTH, ngay);
        //   calendar.set(Calendar.YEAR, nam);

        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.text_chonNgay) {
            mKhongLich.setText("");
            new AsynListLich1().execute();
            final DatePickerDialog.OnDateSetListener dateSetListener =
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                int dayOfMonth) {
                            mCalendar = Calendar.getInstance();
                            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            mCalendar.set(Calendar.MONTH, month);
                            mCalendar.set(Calendar.YEAR, year);
                            updateLich(mCalendar.getTime());
                        }
                    };

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener,
                    mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
        if (v.getId() == R.id.text_all) {
            mMgay.setText("");
            mKhongLich.setText("");
            new AsynListLich().execute();
        }
    }

    public void updateLich(Date date) {

        List<LichKham> list = new ArrayList<>();

        mMgay.setText(mFormat.format(date));

        for (LichKham lichKham : mList) {
            if (lichKham.getNgay().equals(mMgay.getText().toString())) {
                list.add(lichKham);
            }
        }
        if (list.size() == 0) {
            mKhongLich.setText("Không có lịch cho ngày này");
        }
        update(list);
    }

    public class AsynListLich extends AsyncTask<Void, JSONObject, List<LichKham>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LichKham> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<LichKham> list = new ArrayList<>();
            try {

                JSONObject jsonObj = api.ListTime(String.valueOf(mPhongKham.getMaPhongKham()));

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    LichKham lichKham = new LichKham();
                    lichKham.setMa(jsonObj.getString("Id"));
                    lichKham.setMaPk(mPhongKham.getMaPhongKham());
                    lichKham.setTenTK(mUser);
                    lichKham.setNgay(jsonObj.getString("startTG"));
                    lichKham.setTgBatDau(jsonObj.getString("startTG"));
                    lichKham.setTgKetThuc(jsonObj.getString("endTG"));
                    lichKham.setMota(jsonObj.getString("MoTaTime"));
                    lichKham.setStart(jsonObj.getString("startTG"));
                    lichKham.setEnd(jsonObj.getString("endTG"));

                    list.add(lichKham);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<LichKham> result) {
            super.onPostExecute(result);
            update(result);
        }
    }

    public void update(final List<LichKham> lichKhams) {
        mList.clear();
        Calendar calendar = Calendar.getInstance();

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentNgay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentThang = calendar.get(Calendar.MONTH);

        for (LichKham lichKham : lichKhams) {
            int thang = Integer.parseInt(lichKham.getNgay().substring(3, 5));
            int ngay = Integer.parseInt(lichKham.getNgay().substring(0, 2));
            int gio = Integer.parseInt(lichKham.getTgBatDau().substring(0, 2));
            if (thang > 5 || (thang == 5 && ngay > currentNgay) || (thang == 5
                    && ngay == currentNgay
                    && gio > currentHour)) {
                mList.add(lichKham);
            }
        }
        if (mList.size() == 0) {
            Toast.makeText(getActivity(), "Không có lịch hiện tại", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();
    }

    public class AsynDatLich extends AsyncTask<LichKham, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang xử lý ...");
                progressDialog.show();
            }
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(LichKham... params) {
            RestAPI api = new RestAPI();
            try {
                api.DatLichKham(params[0].getTenTK(), params[0].getMaPk(), params[0].getStart(),
                        params[0].getEnd(), params[0].getMota());
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getActivity().getApplicationContext(), "Đăng kí thành công !",
                    Toast.LENGTH_SHORT).show();
            new AsynListLich().execute();
            progressDialog.dismiss();
        }
    }

    public class AsynListLich1 extends AsyncTask<Void, JSONObject, List<LichKham>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LichKham> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<LichKham> list = new ArrayList<>();
            try {

                JSONObject jsonObj = api.ListTime(String.valueOf(mPhongKham.getMaPhongKham()));

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    LichKham lichKham = new LichKham();
                    lichKham.setMa(jsonObj.getString("Id"));
                    lichKham.setMaPk(mPhongKham.getMaPhongKham());
                    lichKham.setTenTK(mUser);
                    lichKham.setNgay(jsonObj.getString("startTG"));
                    lichKham.setTgBatDau(jsonObj.getString("startTG"));
                    lichKham.setTgKetThuc(jsonObj.getString("endTG"));
                    lichKham.setMota(jsonObj.getString("MoTaTime"));
                    lichKham.setStart(jsonObj.getString("startTG"));
                    lichKham.setEnd(jsonObj.getString("endTG"));

                    list.add(lichKham);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<LichKham> result) {
            super.onPostExecute(result);
            update1(result);
        }
    }

    public void update1(final List<LichKham> lichKhams) {
        mList.clear();
        mList.addAll(lichKhams);
    }
}
