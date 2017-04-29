package com.framgia.capstone.ui.chitietthuoc;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.databinding.ActivityDetailBenhBinding;
import com.framgia.capstone.databinding.ActivityDetailThuocBinding;

/**
 * Created by Age on 4/29/2017.
 */

public class ChiTietThuoc extends AppCompatActivity {
    private static final String EXTRA_THUOC = "EXTRA_THUOC";
    private Thuoc mThuoc;
    private ActivityDetailThuocBinding mBinding;

    public static Intent getThuocIntent(Context context, Thuoc thuoc) {
        Intent intent = new Intent(context, ChiTietThuoc.class);
        intent.putExtra(EXTRA_THUOC, thuoc);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_thuoc);
        mThuoc = (Thuoc) getIntent().getSerializableExtra(EXTRA_THUOC);
        setTitle(mThuoc.getTenThuoc());
        setUpView();
        mBinding.setViewModel(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setUpView() {
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public String getHinhAnh(){
        return mThuoc.getHinhAnh();
    }

    public String getTacDung(){
        return mThuoc.getTacDung();
    }

    public String getTenLoaiThuoc(){
        return mThuoc.getTenLoaiThuoc();
    }

    public String getMaVach(){
        return mThuoc.getMaVach();
    }

    public String getMaHinh(){
        return mThuoc.getMaHinh();
    }

    public String getChongChiDinh(){
        return mThuoc.getChongChiDinh();
    }

    public String getGia(){
        return mThuoc.getGia()+"";
    }
}
