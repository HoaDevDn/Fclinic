package com.framgia.capstone.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.BenhRealm;
import com.framgia.capstone.data.model.ThuocRealm;
import com.framgia.capstone.ui.chonphongkham.ChonPhongKhamActivity;
import com.framgia.capstone.ui.login.LoginActivity;
import com.framgia.capstone.ui.nhathuoc.NhaThuocFragment;
import com.framgia.capstone.ui.timkiem.TimKiemActivity;
import com.framgia.capstone.ui.trangchinh.TrangChinhFragment;
import com.framgia.capstone.utils.RestAPI;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.framgia.capstone.utils.SharedPreferencesUtils.deletePhongKham;
import static com.framgia.capstone.utils.SharedPreferencesUtils.deleteUser;
import static com.framgia.capstone.utils.SharedPreferencesUtils.loadUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private String mUser;
    private Realm mRealm;
    private List<ThuocRealm> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        mRealm = Realm.getDefaultInstance();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mUser = loadUser(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addFragment(new TrangChinhFragment(), R.string.title_trangchu);

        Snackbar snackbar =
                Snackbar.make(findViewById(android.R.id.content), "Xin chào" + " " + mUser,
                        Snackbar.LENGTH_LONG);
        snackbar.show();

        new AsyncDanhSach().execute();

        new AsyncDanhSachBenh().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(TimKiemActivity.getInstant(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                addFragment(new TrangChinhFragment(), R.string.title_trangchu);
                break;
            case R.id.nav_timnhathuoc:
                addFragment(new NhaThuocFragment(), R.string.title_tim_nha_thuoc);
                break;
            case R.id.nav_phongkham:
                startActivity(new Intent(this, ChonPhongKhamActivity.class));
                deletePhongKham(this);
                this.finish();
                break;
            case R.id.nav_logout:
                startActivity(new Intent(this, LoginActivity.class));
                deleteUser(this);
                this.finish();
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFragment(Fragment fragment, int title) {
        addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.main);
        setTitle(title);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }

    public class AsyncDanhSach extends AsyncTask<Void, JSONObject, List<ThuocRealm>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            delete();
        }

        @Override
        protected List<ThuocRealm> doInBackground(Void... params) {
            RestAPI api = new RestAPI();
            try {
                mList = new ArrayList<>();

                JSONObject jsonObj = api.LayDanhSachThuoc();

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    ThuocRealm thuocRealm = new ThuocRealm();

                    thuocRealm.setTenThuoc(jsonObj.getString("TenThuoc"));
                    thuocRealm.setMaThuoc(jsonObj.getString("MaThuoc"));
                    thuocRealm.setHinhAnh(jsonObj.getString("HinhAnh"));
                    thuocRealm.setTacDung(jsonObj.getString("TacDung"));
                    thuocRealm.setGia(jsonObj.getString("Gia"));
                    thuocRealm.setChongChiDinh(jsonObj.getString("ChongChiDinh"));

                    mList.add(thuocRealm);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return mList;
        }

        @Override
        protected void onPostExecute(final List<ThuocRealm> result) {
            super.onPostExecute(result);
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(result);
                }
            });
        }
    }

    public class AsyncDanhSachBenh extends AsyncTask<Void, JSONObject, List<BenhRealm>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            deleteBenh();
        }

        @Override
        protected List<BenhRealm> doInBackground(Void... params) {
            RestAPI api = new RestAPI();

            List<BenhRealm> benhRealms = new ArrayList<>();
            try {
                benhRealms = new ArrayList<>();

                JSONObject jsonObj = api.LayDanhSachBenh();

                JSONArray jsonArray = jsonObj.getJSONArray("Value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getJSONObject(i);

                    BenhRealm benhRealm = new BenhRealm();
                    benhRealm.setMaBenh(jsonObj.getString("MaBenh"));
                    benhRealm.setHinhAnh(jsonObj.getString("HinhAnh"));
                    benhRealm.setCachDieuTri(jsonObj.getString("CachDieuTri"));
                    benhRealm.setTrieuChung(jsonObj.getString("TrieuChung"));
                    benhRealm.setTenBenh(jsonObj.getString("TenBenh"));

                    benhRealms.add(benhRealm);
                }
            } catch (Exception e) {
                Log.d("Loi", e.getMessage());
            }
            return benhRealms;
        }

        @Override
        protected void onPostExecute(final List<BenhRealm> result) {
            super.onPostExecute(result);
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(result);
                }
            });
        }
    }

    public void delete() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ThuocRealm> thuocRealms = realm.where(ThuocRealm.class).findAll();
                if (thuocRealms.size() != 0) {
                    thuocRealms.deleteAllFromRealm();
                }
            }
        });
    }

    public void deleteBenh() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<BenhRealm> thuocRealms = realm.where(BenhRealm.class).findAll();
                if (thuocRealms.size() != 0) {
                    thuocRealms.deleteAllFromRealm();
                }
            }
        });
    }
}
