package com.framgia.capstone.ui.timkiem;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.ThuocRepository;
import com.framgia.capstone.databinding.ActivitySearchBinding;
import com.framgia.capstone.ui.adapter.ThuocAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public class TimKiemActivity extends AppCompatActivity
        implements TimKiemContract.View, SearchView.OnQueryTextListener {

    private TimKiemContract.Presenter mPresenter;
//    private ThuocAdapter mAdapter;
    private ObservableField<ThuocAdapter> mAdapter  = new ObservableField<>();
    private List<Thuoc> mList = new ArrayList<>();
    private String LoaiTK[] = { "Tìm kiếm theo tên", "Tìm kiếm theo triệu chứng" };

    public static Intent getInstant(Context context) {
        return new Intent(context, TimKiemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search);
        mPresenter = new TimKiemPresenter(this, ThuocRepository.getInstance(this));
        binding.setViewModel(this);
//        mAdapter=new ThuocAdapter(this, mList);
        ArrayAdapter spinnerAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, LoaiTK);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        binding.spinner.setAdapter(spinnerAdapter);
//        binding.recycleThuoc.setAdapter(mAdapter);
//        binding.recycleThuoc.setLayoutManager(
//                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        binding.recycleThuoc.setHasFixedSize(true);
        mAdapter.set(new ThuocAdapter(this, mList));
        mPresenter.start();
    }

    public ObservableField<ThuocAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void start() {
    }

    @Override
    public void setPresenter(TimKiemContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showThuoc(List<Thuoc> list) {
        mAdapter.get().updateData(list);
        Log.d("aaaaa",list.size()+"");
    }

    @Override
    public void xayraLoi() {

    }
}
