package com.framgia.capstone.ui.datlich;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.LichKham;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 30/04/2017.
 */

public class LichTrongFragment extends Fragment implements LichTrongAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private LichTrongAdapter mAdapter;
    private List<LichKham> mList = new ArrayList<>();

    public LichTrongFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
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

        View view = inflater.inflate(R.layout.fragment_lichtrong, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_lichtrong);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LichTrongAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onDangKy(LichKham lichKham) {
        Toast.makeText(getActivity(), "ssssssss", Toast.LENGTH_SHORT).show();
    }
}
