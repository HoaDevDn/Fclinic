package com.framgia.capstone.ui.toathuoc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.CTToaThuoc;
import com.framgia.capstone.data.model.NhacThuoc;
import com.framgia.capstone.data.model.Status;
import com.framgia.capstone.data.model.ToaThuoc;
import com.framgia.capstone.utils.RestAPI;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChiTietToaThuocFragment extends Fragment
        implements View.OnClickListener, ChiTietToaThuocAdapter.ItemClickListener {
    ImageView back;

    TextView mTenToa;
    TextView mTenUser;
    TextView mMota;
    private TextView alarmTextView;

    private PendingIntent mPendingIntent;

    Realm mRealm;
    Switch mSwitch;

    private int mMatoa;

    private static ChiTietToaThuocFragment inst;

    private ToaThuoc mToaThuoc;

    private RecyclerView mRecyclerView;
    private ChiTietToaThuocAdapter mAdapter;
    private List<CTToaThuoc> mCTToaThuocs = new ArrayList<>();

    private RecyclerView mRecyclerViewTime;
    private List<NhacThuoc> mNhacThuocs = new ArrayList<>();
    private NhacThuocAdapter mNhacThuocAdapter;

    public static ChiTietToaThuocFragment newInstance(ToaThuoc toaThuoc) {
        ChiTietToaThuocFragment fragment = new ChiTietToaThuocFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("aaaa", toaThuoc);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*for (int i = 0; i < 5; i++) {
            CTToaThuoc toaThuoc = new CTToaThuoc();
            toaThuoc.setTenThuoc("Levothyroxine");
            toaThuoc.setMoTa("Ngày 3 lần, mỗi lần 1 viên");
            toaThuoc.setSoLuong(3);
            mCTToaThuocs.add(toaThuoc);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_toa_thuoc, container, false);

     /*   Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);
*/

        mToaThuoc = (ToaThuoc) getArguments().getSerializable("aaaa");

        mMatoa = mToaThuoc.getMaToaThuoc();

        Realm.init(getActivity());
        mRealm = Realm.getDefaultInstance();

      /*  for (int i = 0; i < 2; i++) {
            NhacThuoc nhacThuoc = new NhacThuoc();
            nhacThuoc.setTime("10:10 PM" + " " + i);
            nhacThuoc.setId(1 + i);
            nhacThuoc.setMatoa(mToaThuoc.getMaToaThuoc());
            mNhacThuocs.add(nhacThuoc);
        }*/

        mTenToa = (TextView) view.findViewById(R.id.text_tentoathuoc_ct);
        mTenUser = (TextView) view.findViewById(R.id.text_user_ct);
        mMota = (TextView) view.findViewById(R.id.text_mota_ct);

        mTenToa.setText(mToaThuoc.getTenToa() + "");
        mTenUser.setText(mToaThuoc.getTenUser() + "");
        mMota.setText(mToaThuoc.getMoTa() + "");

        mSwitch = (Switch) view.findViewById(R.id.switch1);
        mSwitch.setOnClickListener(this);

        back = (ImageView) view.findViewById(R.id.image_back);
        back.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_listThuoc);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChiTietToaThuocAdapter(getActivity(), mCTToaThuocs, this);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        mRecyclerViewTime = (RecyclerView) view.findViewById(R.id.recycle_time);
        mRecyclerViewTime.setLayoutManager(manager2);
        mRecyclerViewTime.setHasFixedSize(true);
        mNhacThuocAdapter = new NhacThuocAdapter(getActivity(), mNhacThuocs);
        mRecyclerViewTime.setAdapter(mNhacThuocAdapter);

        new AsynListThuoc().execute();

        new AsynListNhacThuoc().execute();

        if (getStatusSW().getTrangThai() == 0) {
            mSwitch.setChecked(true);
            start();
        }

        if (getStatusSW().getTrangThai() == 1) mSwitch.setChecked(false);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_back) {
            addFragment(ToaThuocFragment.newInstance());
        }

        if ((mSwitch.isChecked())) {
            //    Toast.makeText(getActivity(), "On", Toast.LENGTH_SHORT).show();
            start();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Status status = realm.where(Status.class).equalTo("mMaToa", mMatoa).findFirst();
                    status.setTrangThai(1);
                }
            });
        } else {
            //   Toast.makeText(getActivity(), "Off", Toast.LENGTH_SHORT).show();
            cancel();

            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Status status = realm.where(Status.class).equalTo("mMaToa", mMatoa).findFirst();
                    status.setTrangThai(0);
                }
            });
        }
    }

    public void addFragment(Fragment fragment) {
        addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,
                R.id.trang_chinh);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_bottom_in, R.anim.slide_bottom_out)
                .replace(frameId, fragment)
                .commit();
    }

    @Override
    public void onClick(int position) {
        startActivity(
                new Intent(CTThuoc.getThuocIntent(getActivity(), mCTToaThuocs.get(position))));
    }

    public class AsynListThuoc extends AsyncTask<Void, JSONObject, List<CTToaThuoc>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<CTToaThuoc> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<CTToaThuoc> list = new ArrayList<>();
            try {
                list = new ArrayList<>();

                JSONObject jsonObj = api.ListChiTietToaThuoc(mMatoa);

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    CTToaThuoc toaThuoc = new CTToaThuoc();
                    toaThuoc.setSoLuong(jsonObj.getString("SoLuong"));
                    toaThuoc.setMoTa(jsonObj.getString("MoTaCTTT"));
                    toaThuoc.setTenThuoc(jsonObj.getString("TenThuoc"));
                    toaThuoc.setMaToa(mMatoa);
                    list.add(toaThuoc);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<CTToaThuoc> result) {
            super.onPostExecute(result);
            updateDataListThuoc(result);
        }
    }

    public void updateDataListThuoc(List<CTToaThuoc> toaThuocs) {
        mCTToaThuocs.addAll(toaThuocs);
        mAdapter.notifyDataSetChanged();
    }

    public class AsynListNhacThuoc extends AsyncTask<Void, JSONObject, List<NhacThuoc>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<NhacThuoc> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<NhacThuoc> list = new ArrayList<>();
            try {
                list = new ArrayList<>();

                JSONObject jsonObj = api.ListNhacNhoUongThuoc(mMatoa);

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    NhacThuoc nhacThuoc = new NhacThuoc();
                    nhacThuoc.setMatoa(mMatoa);
                    nhacThuoc.setTime(jsonObj.getString("Time"));
                    nhacThuoc.setId(jsonObj.getInt("IdRemindUongThuoc"));

                    list.add(nhacThuoc);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<NhacThuoc> result) {
            super.onPostExecute(result);
            updateDataListNhacThuoc(result);

            mRealm.executeTransaction(new Realm.Transaction() {
                RealmResults<NhacThuoc> realms =
                        mRealm.where(NhacThuoc.class).equalTo("mMatoa", mMatoa).findAll();

                @Override
                public void execute(Realm realm) {
                    if (realms.size() == 0) {
                        for (NhacThuoc nhacThuoc : result) {
                            realm.insertOrUpdate(nhacThuoc);
                        }
                    }
                }
            });
        }
    }

    public void updateDataListNhacThuoc(List<NhacThuoc> nhacThuocs) {
        mNhacThuocs.addAll(nhacThuocs);
        mNhacThuocAdapter.notifyDataSetChanged();
    }

    public RealmResults<NhacThuoc> getNhacThuoc() {
        RealmResults<NhacThuoc> realms =
                mRealm.where(NhacThuoc.class).equalTo("mMatoa", mMatoa).findAll();
        return realms;
    }

    public Status getStatusSW() {
        Status status = mRealm.where(Status.class).equalTo("mMaToa", mMatoa).findFirst();
        return status;
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < getNhacThuoc().size(); i++) {

            NhacThuoc nhacThuoc = getNhacThuoc().get(i);

            int gio = Integer.parseInt(nhacThuoc.getTime().substring(0, 2));
            int phut = Integer.parseInt(nhacThuoc.getTime().substring(3, 5));

            Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(getActivity(), i, alarmIntent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, gio);
            calendar.set(Calendar.MINUTE, phut);

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, mPendingIntent);
        }

      /*  Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 31);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, mPendingIntent);*/

    }

    public void cancel() {

        Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(mPendingIntent);
    }
}
