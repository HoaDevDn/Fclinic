package com.framgia.capstone.ui.chitietbenh;

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
import com.framgia.capstone.databinding.ActivityDetailBenhBinding;

/**
 * Created by Age on 4/29/2017.
 */

public class ChiTietBenh extends AppCompatActivity {
    private static final String EXTRA_BENH = "EXTRA_BENH";
    private Benh mBenh;
    private ActivityDetailBenhBinding mBinding;

    public static Intent getBenhIntent(Context context, Benh benh) {
        Intent intent = new Intent(context, ChiTietBenh.class);
        intent.putExtra(EXTRA_BENH, benh);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_benh);
        mBenh = (Benh) getIntent().getSerializableExtra(EXTRA_BENH);
        setTitle(mBenh.getTenBenh());
        setUpView();
        mBinding.setViewModel(this);
    }

    public String getHinhAnh() {
        return mBenh.getHinhAnh();
    }

    public String getTrieuChung() {
        return mBenh.getTrieuChung();
    }

    public String getCachDieuTri() {
        return mBenh.getCachDieuTri();
    }

    public String getTenBenh() {
        return mBenh.getTenBenh();
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
}
