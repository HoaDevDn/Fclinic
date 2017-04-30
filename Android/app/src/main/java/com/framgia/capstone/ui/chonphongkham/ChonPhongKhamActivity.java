package com.framgia.capstone.ui.chonphongkham;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.databinding.ActivityChonPhongKhamBinding;
import com.framgia.capstone.ui.adapter.OnItemPhongKhamClickListener;
import com.framgia.capstone.ui.adapter.PhongKhamAdapter;
import com.framgia.capstone.ui.adapter.ThuocAdapter;
import com.framgia.capstone.ui.home.MainActivity;
import java.util.ArrayList;
import java.util.List;
import static com.framgia.capstone.utils.SharedPreferencesUtils.savePhongKham;

/**
 * Created by Age on 4/30/2017.
 */

public class ChonPhongKhamActivity extends AppCompatActivity {
    private ActivityChonPhongKhamBinding mBinding;
    private List<PhongKham> mPhongKhams=new ArrayList<>();
    private List<String> mTenPhongKhams=new ArrayList<>();
    private PhongKham mPhongKham;
    private ArrayAdapter mArrayAdapter;
    private ArrayAdapter mArrayAdapterTenPhongKham;
    private PhongKhamAdapter mAdapter;

    public static Intent getChiTietThuocIntent(Context context) {
        return new Intent(context, ChonPhongKhamActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chon_phong_kham);
        setTitle("Chọn phòng khám");
        setUpView();
        mAdapter=new PhongKhamAdapter(this, mPhongKhams);
        mBinding.recycle.setAdapter(mAdapter);
        loadPhongKham();
        mAdapter.setOnItemClickListener(new OnItemPhongKhamClickListener() {
            @Override
            public void onItemClick(PhongKham phongKham) {
                savePhongKham(getApplication(), phongKham);
                Intent i=new Intent(getApplication(), MainActivity.class);
                startActivity(i);
            }
        });
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

    public void loadPhongKham(){
        List<PhongKham> list=new ArrayList<>();
        list.add(new PhongKham(1,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(2,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(3,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(4,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(5,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(6,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(7,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(8,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(9,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(10,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(11,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(12,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(13,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(14,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(16,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(15,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(17,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(18,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(19,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        list.add(new PhongKham(20,"Phòng khám Vĩnh Trung","Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám Mô tả phòng khám ","http://dangkydoanhnghiep.net.vn/public/userfiles/images/da-khoa-2.jpg","123 Hoàng Diệu","0123456789"));
        updateData(list);
    }

    public void updateData(List<PhongKham> phongKham){
        mPhongKhams.addAll(phongKham);
        mAdapter.notifyDataSetChanged();
    }
}
