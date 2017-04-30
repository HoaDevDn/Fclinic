package com.framgia.capstone.ui.datlich;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.LichKham;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 30/04/2017.
 */

public class LichDaDatFragment extends Fragment implements LIchDaDatAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private LIchDaDatAdapter mAdapter;
    private List<LichKham> mList = new ArrayList<>();

    public LichDaDatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            LichKham lichKham = new LichKham();
            lichKham.setMota("Ngay thu " + i);
            lichKham.setNgay("10/10/2017");
            lichKham.setTgBatDau("10:10");
            lichKham.setTgKetThuc("11:12");
            mList.add(lichKham);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lichdadat, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_lichđadat);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LIchDaDatAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onClick(int position) {

    }
}
