package com.framgia.capstone.ui.chonphongkham;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.databinding.ActivityChonPhongKhamBinding;
import com.framgia.capstone.ui.adapter.OnItemPhongKhamClickListener;
import com.framgia.capstone.ui.adapter.PhongKhamAdapter;
import com.framgia.capstone.ui.gioithieu.AdapterGioiThieu;
import com.framgia.capstone.ui.home.MainActivity;
import com.framgia.capstone.utils.ZoomOutPageTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.capstone.utils.SharedPreferencesUtils.savePhongKham;

/**
 * Created by Age on 4/30/2017.
 */

public class ChonPhongKhamActivity extends AppCompatActivity {
    private ActivityChonPhongKhamBinding mBinding;
    private List<PhongKham> mPhongKhams = new ArrayList<>();
    private List<String> mTenPhongKhams = new ArrayList<>();
    private PhongKham mPhongKham;
    private ArrayAdapter mArrayAdapter;
    private ArrayAdapter mArrayAdapterTenPhongKham;
    private PhongKhamAdapter mAdapter;

    AdapterGioiThieu mAdapterGioiThieu;
    ViewPager mPager;
    InkPageIndicator mIndicator;

    public static Intent getChiTietThuocIntent(Context context) {
        return new Intent(context, ChonPhongKhamActivity.class);
    }

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

        setTitle("Chọn phòng khám");
        mAdapter = new PhongKhamAdapter(this, mPhongKhams);
        mBinding.recycle.setAdapter(mAdapter);
        loadPhongKham();
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
    }

    public void updateData(List<PhongKham> phongKham) {
        mPhongKhams.addAll(phongKham);
        mAdapter.notifyDataSetChanged();
    }
}
