package com.framgia.capstone.ui.datlich;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.CTToaThuoc;
import com.framgia.capstone.data.model.LichKham;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.utils.RestAPI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadPhongKham;

/**
 * Created by tri on 30/04/2017.
 */

public class LichTrongFragment extends Fragment implements LichTrongAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private LichTrongAdapter mAdapter;
    private List<LichKham> mList = new ArrayList<>();

    private PhongKham mPhongKham;
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

        mPhongKham = loadPhongKham(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_lichtrong);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LichTrongAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);

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

                JSONObject jsonObj = api.ListTime();

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);


             //       list.add(toaThuoc);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<LichKham> result) {
            super.onPostExecute(result);
        }
    }
}
