package com.framgia.capstone.ui.toathuoc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.framgia.capstone.data.model.NhacThuocRealm;
import com.framgia.capstone.data.model.ToaThuoc;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class ChiTietToaThuocFragment extends Fragment
        implements View.OnClickListener, ChiTietToaThuocAdapter.ItemClickListener {
    ImageView back;

    TextView mTenToa;
    TextView mTenUser;
    TextView mMota;

    Realm mRealm;
    Switch mSwitch;

    SharedPreferences preferences;

    private ToaThuoc mToaThuoc;

    private RecyclerView mRecyclerView;
    private ChiTietToaThuocAdapter mAdapter;
    private List<CTToaThuoc> mList = new ArrayList<>();

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
        for (int i = 0; i < 5; i++) {
            CTToaThuoc toaThuoc = new CTToaThuoc();
            toaThuoc.setTenThuoc("Levothyroxine");
            toaThuoc.setMoTa("Ngày 3 lần, mỗi lần 1 viên");
            toaThuoc.setSoLuong(3);
            mList.add(toaThuoc);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_toa_thuoc, container, false);

        mToaThuoc = (ToaThuoc) getArguments().getSerializable("aaaa");

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
        mAdapter = new ChiTietToaThuocAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        mRecyclerViewTime = (RecyclerView) view.findViewById(R.id.recycle_time);
        mRecyclerViewTime.setLayoutManager(manager2);
        mRecyclerViewTime.setHasFixedSize(true);
        mNhacThuocAdapter = new NhacThuocAdapter(getActivity(), mNhacThuocs);
        mRecyclerViewTime.setAdapter(mNhacThuocAdapter);

       /* mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<NhacThuoc> realms =
                        mRealm.where(NhacThuoc.class).equalTo("mMatoa", mToaThuoc.getMaToaThuoc()).findAll();

                if (realms.size() == 0) {
                    for (NhacThuoc nhacThuoc : mNhacThuocs) {
                        nhacThuoc.setStatus(1);
                        realm.insertOrUpdate(nhacThuoc);
                    }
                } else {
                    for (NhacThuoc nhacThuoc : mNhacThuocs) {
                        for (NhacThuoc thuoc : realms) {
                            if (thuoc.getId() != nhacThuoc.getId()) {
                                nhacThuoc.setStatus(1);
                                realm.insertOrUpdate(nhacThuoc);
                            }
                        }
                    }
                }
            }
        });

        Toast.makeText(getActivity(), getList().size() + "", Toast.LENGTH_SHORT).show();
*/


      /*  if (true)
        {
            mSwitch.setChecked(true);
        } else {
            mSwitch.setChecked(false);
        }*/

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_back) {
            addFragment(ToaThuocFragment.newInstance());
        }

        if ((mSwitch.isChecked())) {
            Toast.makeText(getActivity(), "On", Toast.LENGTH_SHORT).show();

            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "Off", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
    }

    public RealmResults<NhacThuoc> getList() {
        RealmResults<NhacThuoc> realms = mRealm.where(NhacThuoc.class).findAll();
        return realms;
    }
}
