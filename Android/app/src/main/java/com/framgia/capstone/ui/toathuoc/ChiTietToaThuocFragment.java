package com.framgia.capstone.ui.toathuoc;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChiTietToaThuocFragment extends Fragment
        implements View.OnClickListener, ChiTietToaThuocAdapter.ItemClickListener {
    ImageView back;

    TextView mTenToa;
    TextView mTenUser;
    TextView mMota;

    Realm mRealm;
    Switch mSwitch;

    private int mMatoa;

    SharedPreferences preferences;

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

        mToaThuoc = (ToaThuoc) getArguments().getSerializable("aaaa");

        mMatoa = mToaThuoc.getMaToaThuoc();

        Realm.init(getActivity());
        mRealm = Realm.getDefaultInstance();

        for (int i = 0; i < 2; i++) {
            NhacThuoc nhacThuoc = new NhacThuoc();
            nhacThuoc.setTime("10:10 PM" + " " + i);
            nhacThuoc.setId(1 + i);
            nhacThuoc.setMatoa(mToaThuoc.getMaToaThuoc());
            mNhacThuocs.add(nhacThuoc);
        }

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

        mRealm.executeTransaction(new Realm.Transaction() {
            RealmResults<NhacThuoc> realms =
                    mRealm.where(NhacThuoc.class).equalTo("mMatoa", mMatoa).findAll();

            @Override
            public void execute(Realm realm) {
                if (realms.size() == 0) {
                    for (NhacThuoc nhacThuoc : mNhacThuocs) {
                        realm.insertOrUpdate(nhacThuoc);
                    }
                }
            }
        });

      /*  if (getNhacThuoc().size() == 0) {
            return null;
        } else {
            Toast.makeText(getActivity(), getNhacThuoc().size() + "", Toast.LENGTH_SHORT).show();
        }*/

        if (getStatusSW().getTrangThai() == 1) mSwitch.setChecked(true);

        if (getStatusSW().getTrangThai() == 0) mSwitch.setChecked(false);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_back) {
            addFragment(ToaThuocFragment.newInstance());
        }

        if ((mSwitch.isChecked())) {
            //    Toast.makeText(getActivity(), "On", Toast.LENGTH_SHORT).show();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Status status = realm.where(Status.class).equalTo("mMaToa", mMatoa).findFirst();
                    status.setTrangThai(1);
                }
            });
        } else {
            //   Toast.makeText(getActivity(), "Off", Toast.LENGTH_SHORT).show();

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

    public RealmResults<NhacThuoc> getNhacThuoc() {
        RealmResults<NhacThuoc> realms =
                mRealm.where(NhacThuoc.class).equalTo("mMatoa", mMatoa).findAll();
        return realms;
    }

    public Status getStatusSW() {
        Status status = mRealm.where(Status.class).equalTo("mMaToa", mMatoa).findFirst();
        return status;
    }
}
