package com.framgia.capstone.ui.toathuoc;

import android.app.ProgressDialog;
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
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Status;
import com.framgia.capstone.data.model.ToaThuoc;
import com.framgia.capstone.utils.RestAPI;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadUser;

public class ToaThuocFragment extends Fragment implements ToaThuocAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private ToaThuocAdapter mAdapter;
    private List<ToaThuoc> mList = new ArrayList<>();
    private String mUser;
    private Realm mRealm;

    public ToaThuocFragment() {
    }

    public static ToaThuocFragment newInstance() {
        return new ToaThuocFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* for (int i = 0; i < 10; i++) {
            ToaThuoc toaThuoc = new ToaThuoc();
            toaThuoc.setMaToaThuoc(1);
            toaThuoc.setMoTa("Toa thuốc ho");
            toaThuoc.setTenToa("Thuốc Ho");
            toaThuoc.setTenUser("TMT");
            mList.add(toaThuoc);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toa_thuoc, container, false);

        mUser = loadUser(getActivity());

        Realm.init(getActivity());
        mRealm = Realm.getDefaultInstance();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_toathuoc);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ToaThuocAdapter(getActivity(), mList, this);
        mRecyclerView.setAdapter(mAdapter);

        new AsyncDanhSachToaThuoc().execute();

        return view;
    }

    @Override
    public void onClick(final int position) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Status sta = realm.where(Status.class)
                        .equalTo("mMaToa", mList.get(position).getMaToaThuoc())
                        .findFirst();
                if (sta == null) {
                    Status status = new Status();
                    status.setTrangThai(0);
                    status.setMaToa(mList.get(position).getMaToaThuoc());
                    realm.insertOrUpdate(status);
                }
            }
        });
        addFragment(ChiTietToaThuocFragment.newInstance(mList.get(position)));
    }

    public void addFragment(Fragment fragment) {
        addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,
                R.id.trang_chinh);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_left_in, R.anim.slide_top_out)
                .replace(frameId, fragment)
                .commit();
    }

    public class AsyncDanhSachToaThuoc extends AsyncTask<Void, JSONObject, List<ToaThuoc>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ToaThuoc> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<ToaThuoc> list = new ArrayList<>();
            try {
                list = new ArrayList<>();

                JSONObject jsonObj = api.ListToaThuoc(mUser);

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    ToaThuoc toaThuoc = new ToaThuoc();
                    toaThuoc.setMaToaThuoc(jsonObj.getInt("MaToaThuoc"));
                    toaThuoc.setTenToa(jsonObj.getString("TenToaThuoc"));
                    toaThuoc.setTenUser(mUser);
                    toaThuoc.setMoTa(jsonObj.getString("MoTa"));

                    list.add(toaThuoc);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<ToaThuoc> result) {
            super.onPostExecute(result);
            updateData(result);
        }
    }

    public void updateData(List<ToaThuoc> toaThuocs) {
        mList.clear();
        for (int i = toaThuocs.size() - 1; i >= 0; i--) {
            ToaThuoc toaThuoc = toaThuocs.get(i);
            mList.add(toaThuoc);
        }
        mAdapter.notifyDataSetChanged();
    }
}
