package com.framgia.capstone.ui.timkiem;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.BenhRealm;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.model.ThuocRealm;
import com.framgia.capstone.data.resource.BenhRepository;
import com.framgia.capstone.data.resource.ThuocRepository;
import com.framgia.capstone.databinding.ActivitySearchBinding;
import com.framgia.capstone.ui.adapter.BenhAdapter;
import com.framgia.capstone.ui.adapter.OnItemBenhClickListener;
import com.framgia.capstone.ui.adapter.OnItemThuocClickListener;
import com.framgia.capstone.ui.adapter.ThuocAdapter;
import com.framgia.capstone.ui.chitietbenh.ChiTietBenh;
import com.framgia.capstone.ui.chitietthuoc.ChiTietThuoc;
import com.framgia.capstone.ui.timkiemOCR.TimKiemOCR;
import com.framgia.capstone.utils.BaseActivity;
import io.realm.Realm;
import io.realm.RealmResults;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Age on 4/8/2017.
 */

public class TimKiemActivity extends BaseActivity
        implements TimKiemContract.View, SearchView.OnQueryTextListener {

    private TimKiemContract.Presenter mPresenter;
    private List<Thuoc> mListTamThuoc = new ArrayList<>();
    private ThuocAdapter mAdapterThuoc;
    private BenhAdapter mAdapterBenh;
    private List<Thuoc> mListThuoc = new ArrayList<>();
    private List<Benh> mListBenh = new ArrayList<>();
    private List<Benh> mListTamBenh = new ArrayList<>();
    private String LoaiTK[] = { "Tìm kiếm thuốc theo tên", "Tìm bệnh theo triệu chứng" };
    private String chitietloaiTK;
    private boolean isCheck = true;
    private ActivitySearchBinding mBinding;

    private Realm mRealm;

    public static Intent getInstant(Context context) {
        return new Intent(context, TimKiemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        mRealm = Realm.getDefaultInstance();

        setTitle("Tìm kiếm");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mPresenter = new TimKiemPresenter(this, ThuocRepository.getInstance(this),
                BenhRepository.getInstance(this));
        mBinding.setViewModel(this);

        mAdapterThuoc = new ThuocAdapter(this, mListThuoc);
        mBinding.recycleThuoc.setAdapter(mAdapterThuoc);
        mAdapterThuoc.setOnItemClickListener(new OnItemThuocClickListener() {
            @Override
            public void onItemClick(Thuoc thuoc) {
                startActivity(new Intent(ChiTietThuoc.getThuocIntent(getApplication(), thuoc)));
            }
        });

        mAdapterBenh = new BenhAdapter(this, mListBenh);
        mBinding.recycleBenh.setAdapter(mAdapterBenh);
        mAdapterBenh.setOnItemClickListener(new OnItemBenhClickListener() {
            @Override
            public void onItemClick(Benh benh) {
                startActivity(new Intent(ChiTietBenh.getBenhIntent(getApplication(), benh)));
            }
        });

        mAdapterThuoc.updateData(mListThuoc);

        ArrayAdapter spinnerAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, LoaiTK);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mBinding.spinner.setAdapter(spinnerAdapter);
        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chitietloaiTK = LoaiTK[position];
                mAdapterThuoc = new ThuocAdapter(getApplication(), mListThuoc);
                mAdapterThuoc.notifyDataSetChanged();
                mBinding.recycleThuoc.setAdapter(mAdapterThuoc);
                mAdapterThuoc.setOnItemClickListener(new OnItemThuocClickListener() {
                    @Override
                    public void onItemClick(Thuoc thuoc) {
                        startActivity(
                                new Intent(ChiTietThuoc.getThuocIntent(getApplication(), thuoc)));
                    }
                });

                mAdapterBenh = new BenhAdapter(getApplication(), mListBenh);
                mAdapterBenh.notifyDataSetChanged();
                mBinding.recycleBenh.setAdapter(mAdapterBenh);
                mAdapterBenh.setOnItemClickListener(new OnItemBenhClickListener() {
                    @Override
                    public void onItemClick(Benh benh) {
                        startActivity(
                                new Intent(ChiTietBenh.getBenhIntent(getApplication(), benh)));
                    }
                });

                if (isCheck) {
                    mBinding.setIsVisible(true);
                    isCheck = false;
                } else {
                    mBinding.setIsVisible(false);
                    isCheck = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setUpView();
        mPresenter.start();
        Toast.makeText(this,""+getListThuoc().size(),Toast.LENGTH_SHORT).show();
        loadListThuoc(getListThuoc());
        loadListBenh(getListBenh());
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
        return true;
    }

    @Override
    public void showThuoc(List<Thuoc> list) {
     //   mAdapterThuoc.updateData(list);
    }

    @Override
    public void showBenh(List<Benh> list) {
        mAdapterBenh.updateData(list);
    }

    @Override
    public void xayraLoi() {

    }

    public void timkiem(String query) {
        mListTamThuoc.clear();
        mListTamBenh.clear();
        switch (chitietloaiTK) {
            case "Tìm kiếm thuốc theo tên":
                for (int i = 0; i < mListThuoc.size(); i++) {
                    if (mListThuoc.get(i)
                            .getTenThuoc()
                            .toLowerCase()
                            .contains(query.toLowerCase())) {
                        mListTamThuoc.add(mListThuoc.get(i));
                    }
                }
                mAdapterThuoc = new ThuocAdapter(this, mListTamThuoc);
                mAdapterThuoc.notifyDataSetChanged();
                mBinding.recycleThuoc.setAdapter(mAdapterThuoc);
                mAdapterThuoc.setOnItemClickListener(new OnItemThuocClickListener() {
                    @Override
                    public void onItemClick(Thuoc thuoc) {
                        startActivity(
                                new Intent(ChiTietThuoc.getThuocIntent(getApplication(), thuoc)));
                    }
                });
                break;
            case "Tìm bệnh theo triệu chứng":
                for (int i = 0; i < mListBenh.size(); i++) {
                    if (removeAccent(mListBenh.get(i).getTrieuChung()).toLowerCase()
                            .contains(query.toLowerCase())) {
                        mListTamBenh.add(mListBenh.get(i));
                    }
                }
                mAdapterBenh = new BenhAdapter(this, mListTamBenh);
                mAdapterBenh.notifyDataSetChanged();
                mBinding.recycleBenh.setAdapter(mAdapterBenh);
                mAdapterBenh.setOnItemClickListener(new OnItemBenhClickListener() {
                    @Override
                    public void onItemClick(Benh benh) {
                        startActivity(
                                new Intent(ChiTietBenh.getBenhIntent(getApplication(), benh)));
                    }
                });
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

    public FloatingSearchView.OnSearchListener getQueryTextListener() {
        return new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String keyWord) {
                timkiem(keyWord);
            }
        };
    }

    public FloatingSearchView.OnMenuItemClickListener getOnItemClickListener() {
        return new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
                    Intent intent = new Intent(TimKiemActivity.this, TimKiemOCR.class);
                    startActivity(intent);
                }
            }
        };
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

    public RealmResults<ThuocRealm> getListThuoc() {
        RealmResults<ThuocRealm> realms = mRealm.where(ThuocRealm.class).findAll();
        return realms;
    }

    public RealmResults<BenhRealm> getListBenh() {
        RealmResults<BenhRealm> realms = mRealm.where(BenhRealm.class).findAll();
        return realms;
    }

    public void loadListThuoc(List<ThuocRealm> thuocRealms) {
        if (thuocRealms == null) {
            return;
        }
        List<Thuoc> list = new ArrayList<>();
        for (int i = 0; i < thuocRealms.size(); i++) {
            list.add(new Thuoc(thuocRealms.get(i).getMaThuoc(), thuocRealms.get(i).getMaLoaiThuoc(),
                    thuocRealms.get(i).getTenThuoc(), thuocRealms.get(i).getHinhAnh(),
                    thuocRealms.get(i).getGia(), thuocRealms.get(i).getMaVach(),
                    thuocRealms.get(i).getMaHinh(), thuocRealms.get(i).getTacDung(),
                    thuocRealms.get(i).getChongChiDinh(), thuocRealms.get(i).getTenLoaiThuoc()));
        }
        mAdapterThuoc.updateData(list);
    }

    public void loadListBenh(List<BenhRealm> benhRealms) {
        if (benhRealms == null) {
            return;
        }
        List<Benh> list = new ArrayList<>();
        for (int i = 0; i < benhRealms.size(); i++) {
            list.add(new Benh(benhRealms.get(i).getMaBenh(), benhRealms.get(i).getTenBenh(),
                    benhRealms.get(i).getHinhAnh(), benhRealms.get(i).getTrieuChung(),
                    benhRealms.get(i).getCachDieuTri()));
        }
        mAdapterBenh.updateData(list);
    }
}
