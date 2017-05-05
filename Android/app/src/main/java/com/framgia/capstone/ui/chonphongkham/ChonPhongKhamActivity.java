package com.framgia.capstone.ui.chonphongkham;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.databinding.ActivityChonPhongKhamBinding;
import com.framgia.capstone.ui.adapter.OnItemPhongKhamClickListener;
import com.framgia.capstone.ui.adapter.PhongKhamAdapter;
import com.framgia.capstone.ui.gioithieu.AdapterGioiThieu;
import com.framgia.capstone.ui.home.MainActivity;
import com.framgia.capstone.utils.RestAPI;
import com.framgia.capstone.utils.ZoomOutPageTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.savePhongKham;

/**
 * Created by Age on 4/30/2017.
 */

public class ChonPhongKhamActivity extends AppCompatActivity {
    private ActivityChonPhongKhamBinding mBinding;
    private List<PhongKham> mPhongKhams = new ArrayList<>();
    private PhongKhamAdapter mAdapter;

    AdapterGioiThieu mAdapterGioiThieu;
    ViewPager mPager;
    InkPageIndicator mIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chon_phong_kham);

        mAdapterGioiThieu = new AdapterGioiThieu(getSupportFragmentManager(), this);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setAdapter(mAdapterGioiThieu);
        mIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        new AsyncDanhPk().execute();

        mAdapter = new PhongKhamAdapter(this, mPhongKhams);
        mBinding.recycle.setAdapter(mAdapter);

        //  loadPhongKham();

        mAdapter.setOnItemClickListener(new OnItemPhongKhamClickListener() {
            @Override
            public void onItemClick(PhongKham phongKham) {
                savePhongKham(getApplication(), phongKham);
                Intent i = new Intent(getApplication(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        mBinding.setViewModel(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
/*
    public void loadPhongKham() {
        List<PhongKham> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PhongKham phongKham = new PhongKham();
            phongKham.setHinhAnh(
                    "http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg");
            phongKham.setDiaChi("Nguyễn Văng Linh" + " " + i);
            phongKham.setMoTa("Phong Khàm Mắt" + " " + i);
            phongKham.setSDT("01254544");
            phongKham.setTenPhongKham("Phòng Khám NVL" + " " + i);
            list.add(phongKham);
        }
        updateData(list);
    }*/

    public void updateData(List<PhongKham> phongKham) {
        mPhongKhams.addAll(phongKham);
        mAdapter.notifyDataSetChanged();
    }

    public class AsyncDanhPk extends AsyncTask<Void, JSONObject, List<PhongKham>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<PhongKham> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            List<PhongKham> phongKhams = new ArrayList<>();
            try {
                JSONObject jsonObj = api.ListPhongKham();

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    PhongKham phongKham = new PhongKham();

                    phongKham.setTenPhongKham(jsonObj.getString("TenPhongKham"));
                    phongKham.setMaPhongKham(jsonObj.getInt("MaPhongKham"));
                    phongKham.setHinhAnh(jsonObj.getString("HinhAnh"));
                    phongKham.setSDT(jsonObj.getString("SDT"));
                    phongKham.setMoTa(jsonObj.getString("MoTa"));
                    phongKham.setDiaChi(jsonObj.getString("DiaChi"));

                    phongKhams.add(phongKham);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return phongKhams;
        }

        @Override
        protected void onPostExecute(final List<PhongKham> result) {
            super.onPostExecute(result);
            updateData(result);
        }
    }
}
