package com.framgia.capstone.ui.toathuoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.ToaThuoc;
import java.util.ArrayList;
import java.util.List;

public class ToaThuocFragment extends Fragment implements ToaThuocAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private ToaThuocAdapter mAdapter;
    private List<ToaThuoc> mList = new ArrayList<>();

    public ToaThuocFragment() {
    }

    public static ToaThuocFragment newInstance() {
        return new ToaThuocFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            ToaThuoc toaThuoc = new ToaThuoc();
            toaThuoc.setMoTa("Toa thuốc ho");
            toaThuoc.setTenToa("Thuốc Ho");
            toaThuoc.setTenUser("TMT");
            mList.add(toaThuoc);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toa_thuoc, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_toathuoc);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ToaThuocAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
    }
}
