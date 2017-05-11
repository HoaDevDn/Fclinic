package com.framgia.capstone.ui.datlich;

import android.app.DatePickerDialog;
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
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadPhongKham;

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

    private Calendar mCalendar = Calendar.getInstance();
    private PhongKham mPhongKham;

    SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy");

    public LichTrongFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    /*    for (int i = 0; i < 10; i++) {
            LichKham lichKham = new LichKham();
            lichKham.setMota("Ngay thu " + i);
            lichKham.setNgay("10/10/2017");
            lichKham.setTgBatDau("10:10");
            lichKham.setTgKetThuc("11:12");
            mList.add(lichKham);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lichtrong, container, false);

        mChonNgay = (TextView) view.findViewById(R.id.text_chonNgay);
        mMgay = (TextView) view.findViewById(R.id.text_ngay);
        mAll = (TextView) view.findViewById(R.id.text_all);

        mCalendar = Calendar.getInstance();

        mPhongKham = loadPhongKham(getActivity());

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
        Toast.makeText(getActivity(), "ssssssss", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.text_chonNgay) {
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
                    mCalendar.get(Calendar.DAY_OF_MONTH), mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.YEAR));
            datePickerDialog.show();
        }
        if (v.getId() == R.id.text_all) {
            Toast.makeText(getActivity(), "all" + "", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLich(Date date) {
        List<LichKham> list = new ArrayList<>();
        mMgay.setText(mFormat.format(date));

        String abc = mMgay.getText().toString();

        for (LichKham lichKham : mList) {
            if (lichKham.getNgay().equals(abc)) {
                //     list.add(lichKham);
            }
        }
        Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_SHORT).show();
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
                    lichKham.setMa(jsonObj.getInt("Id"));
                    
                    lichKham.setNgay(jsonObj.getString("Ngay").substring(0, 10));
                    //       lichKham.setNgay(mFormat.format(jsonObj.getString("Ngay")));
                    //           lichKham.setTgBatDau(jsonObj.getString("TGBatDau"));
                    //            lichKham.setTgKetThuc(jsonObj.getString("TGKetThuc"));
                              lichKham.setMota(jsonObj.getString("MoTaTime"));

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

    public void update(List<LichKham> lichKhams) {
        mList.addAll(lichKhams);
        mAdapter.notifyDataSetChanged();
    }
}
