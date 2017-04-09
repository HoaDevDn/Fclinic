package com.framgia.capstone.ui.timkiem;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.BenhRepository;
import com.framgia.capstone.data.resource.ThuocRepository;
import com.framgia.capstone.databinding.ActivitySearchBinding;
import com.framgia.capstone.ui.adapter.BenhAdapter;
import com.framgia.capstone.ui.adapter.ThuocAdapter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Age on 4/8/2017.
 */

public class TimKiemActivity extends AppCompatActivity
        implements TimKiemContract.View, SearchView.OnQueryTextListener {

    private TimKiemContract.Presenter mPresenter;
    private List<Thuoc> mListTamThuoc = new ArrayList<>();
    private ObservableField<ThuocAdapter> mAdapterThuoc  = new ObservableField<>();
    private ObservableField<BenhAdapter> mAdapterBenh  = new ObservableField<>();
    private List<Thuoc> mListThuoc = new ArrayList<>();
    private List<Benh> mListBenh = new ArrayList<>();
    private List<Benh> mListTamBenh = new ArrayList<>();
    private String LoaiTK[] = { "Tìm kiếm theo tên thuốc", "Tìm kiếm theo triệu chứng" };
    private String chitietloaiTK;
    private boolean isCheck=true;
    public static Intent getInstant(Context context) {
        return new Intent(context, TimKiemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySearchBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search);
        mPresenter = new TimKiemPresenter(this, ThuocRepository.getInstance(this), BenhRepository.getInstance(this));
        binding.setViewModel(this);
        ArrayAdapter spinnerAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, LoaiTK);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        binding.spinner.setAdapter(spinnerAdapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chitietloaiTK=LoaiTK[position];
                mAdapterThuoc.set(new ThuocAdapter(getApplication(), mListThuoc));
                mAdapterThuoc.get().notifyDataSetChanged();
                mAdapterBenh.set(new BenhAdapter(getApplication(), mListBenh));
                mAdapterBenh.get().notifyDataSetChanged();
                if(isCheck){
                    binding.setIsVisible(true);
                    isCheck=false;
                }
                else{
                    binding.setIsVisible(false);
                    isCheck=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAdapterThuoc.set(new ThuocAdapter(this, mListThuoc));
        mAdapterBenh.set(new BenhAdapter(this, mListBenh));
        mPresenter.start();
    }

    public ObservableField<ThuocAdapter> getAdapter() {
        return mAdapterThuoc;
    }
    public ObservableField<BenhAdapter> getAdapterBenh() {
        return mAdapterBenh;
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
        timkiem(query);
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
        mAdapterThuoc.get().updateData(list);
    }

    @Override
    public void showBenh(List<Benh> list) {
        mAdapterBenh.get().updateData(list);
    }

    @Override
    public void xayraLoi() {

    }

    public void timkiem(String query){
        mListTamThuoc.clear();
        mListTamBenh.clear();
        switch (chitietloaiTK){
            case "Tìm kiếm theo tên thuốc":
                for (int i = 0; i < mListThuoc.size(); i++){
                    if(mListThuoc.get(i).getTenThuoc().toLowerCase().contains(query.toString().toLowerCase())){
                        mListTamThuoc.add(mListThuoc.get(i));
                    }
                }
                mAdapterThuoc.set(new ThuocAdapter(this, mListTamThuoc));
                mAdapterThuoc.get().notifyDataSetChanged();
                break;
            case "Tìm kiếm theo triệu chứng":
                for (int i = 0; i < mListBenh.size(); i++){
                    if(removeAccent(mListBenh.get(i).getTrieuChung()).toLowerCase().contains(query.toString().toLowerCase())){
                        mListTamBenh.add(mListBenh.get(i));
                    }
                }
                mAdapterBenh.set(new BenhAdapter(this, mListTamBenh));
                mAdapterBenh.get().notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

}
