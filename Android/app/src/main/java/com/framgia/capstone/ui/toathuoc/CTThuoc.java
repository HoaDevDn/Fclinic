package com.framgia.capstone.ui.toathuoc;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.CTToaThuoc;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.model.ThuocRealm;
import com.framgia.capstone.databinding.ActivityThuocBinding;
import io.realm.Realm;

public class CTThuoc extends AppCompatActivity {

    private static final String EXTRA_THUOC = "EXTRA_THUOC";
    private Thuoc mThuoc;
    private ActivityThuocBinding mBinding;
    private CTToaThuoc mCTToaThuoc;
    private Realm mRealm;

    ImageView back;

    public static Intent getThuocIntent(Context context, CTToaThuoc toaThuoc) {
        Intent intent = new Intent(context, CTThuoc.class);
        intent.putExtra("qwe", toaThuoc);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        mRealm = Realm.getDefaultInstance();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thuoc);


        mCTToaThuoc = (CTToaThuoc) getIntent().getSerializableExtra("qwe");

        //  if (mThuoc == null) return;

        mThuoc = getThuoc();
        setTitle(mThuoc.getTenThuoc());
        setUpView();
        mBinding.setViewModel(this);
    }

    private void setUpView() {
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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

    public String getHinhAnh() {
        return mThuoc.getHinhAnh();
    }

    public String getTen() {
        return mThuoc.getTenThuoc();
    }

    public String getTacDung() {
        return mThuoc.getTacDung();
    }

    public String getTenLoaiThuoc() {
        return mThuoc.getTenLoaiThuoc();
    }

    public String getChongChiDinh() {
        return mThuoc.getChongChiDinh();
    }

    public String getGia() {
        return mThuoc.getGia() + "";
    }

    public Thuoc getThuoc() {
        ThuocRealm thuocRealm = mRealm.where(ThuocRealm.class)
                .equalTo("mTenThuoc", mCTToaThuoc.getTenThuoc())
                .findFirst();

        Thuoc thuoc = new Thuoc();
        thuoc.setTenThuoc(thuocRealm.getTenThuoc());
        thuoc.setHinhAnh(thuocRealm.getHinhAnh());
        thuoc.setTenLoaiThuoc(thuocRealm.getTenLoaiThuoc());
        thuoc.setGia(thuocRealm.getGia());
        thuoc.setTacDung(thuocRealm.getTacDung());
        thuoc.setChongChiDinh(thuocRealm.getChongChiDinh());
        return thuoc;
    }


}
