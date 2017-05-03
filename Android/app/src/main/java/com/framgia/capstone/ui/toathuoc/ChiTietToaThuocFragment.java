package com.framgia.capstone.ui.toathuoc;

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
import android.widget.TextView;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.CTToaThuoc;
import com.framgia.capstone.data.model.ToaThuoc;
import java.util.ArrayList;
import java.util.List;

public class ChiTietToaThuocFragment extends Fragment
        implements View.OnClickListener, ChiTietToaThuocAdapter.ItemClickListener {
    ImageView back;

    TextView mTenToa;
    TextView mTenUser;
    TextView mMota;

    private ToaThuoc mToaThuoc;

    private RecyclerView mRecyclerView;
    private ChiTietToaThuocAdapter mAdapter;
    private List<CTToaThuoc> mList = new ArrayList<>();

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

        mTenToa = (TextView) view.findViewById(R.id.text_tentoathuoc_ct);
        mTenUser = (TextView) view.findViewById(R.id.text_user_ct);
        mMota = (TextView) view.findViewById(R.id.text_mota_ct);

        mToaThuoc = (ToaThuoc) getArguments().getSerializable("aaaa");

        mTenToa.setText(mToaThuoc.getTenToa() + "");
        mTenUser.setText(mToaThuoc.getTenUser() + "");
        mMota.setText(mToaThuoc.getMoTa() + "");

        back = (ImageView) view.findViewById(R.id.image_back);
        back.setOnClickListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_listThuoc);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChiTietToaThuocAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_back) {
            addFragment(ToaThuocFragment.newInstance());
        }
    }

    public void addFragment(Fragment fragment) {
        addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,
                R.id.trang_chinh);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
    }
}
