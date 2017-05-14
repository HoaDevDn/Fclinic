package com.framgia.capstone.ui.datlich;

import android.app.ProgressDialog;
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
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.LichKham;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.utils.RestAPI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadPhongKham;
import static com.framgia.capstone.utils.SharedPreferencesUtils.loadUser;

/**
 * Created by tri on 30/04/2017.
 */

public class LichDaDatFragment extends Fragment implements LIchDaDatAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private LIchDaDatAdapter mAdapter;
    private List<LichKham> mList = new ArrayList<>();
    private PhongKham mPhongKham;
    private String mUser;

    public LichDaDatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lichdadat, container, false);

        mPhongKham = loadPhongKham(getActivity());
        mUser = loadUser(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_lichđadat);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LIchDaDatAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onClick(int position) {
    }

    @Override
    public void onHuy(LichKham lichKham) {
        new AsynHuyLich().execute(lichKham);
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

                JSONObject jsonObj = api.LoadDanhSachLichKham(mUser,
                        String.valueOf(mPhongKham.getMaPhongKham()));

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    LichKham lichKham = new LichKham();
                    lichKham.setMa(jsonObj.getString("MaDatLich"));
                    lichKham.setNgay(jsonObj.getString("start"));
                    lichKham.setTgBatDau(jsonObj.getString("start"));
                    lichKham.setTgKetThuc(jsonObj.getString("end"));
                    lichKham.setMota(jsonObj.getString("TenBacSy"));
                    lichKham.setTenPK(jsonObj.getString("TenPhongKham"));

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
        mList.clear();
        for (int i = lichKhams.size() - 1; i >= 0; i--) {
            LichKham lichKham = lichKhams.get(i);
            mList.add(lichKham);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            new AsynListLich().execute();
        }
    }

    public class AsynHuyLich extends AsyncTask<LichKham, Void, Void> {
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
                api.HuyLichKham(Integer.parseInt(params[0].getMa()));
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getActivity().getApplicationContext(), "Hủy lich thành công !",
                    Toast.LENGTH_SHORT).show();
            new AsynListLich().execute();
            progressDialog.dismiss();
        }
    }
}
