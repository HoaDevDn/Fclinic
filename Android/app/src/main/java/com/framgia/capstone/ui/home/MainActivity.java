package com.framgia.capstone.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.ui.chonphongkham.ChonPhongKhamActivity;
import com.framgia.capstone.ui.login.LoginActivity;
import com.framgia.capstone.ui.nhathuoc.NhaThuocFragment;
import com.framgia.capstone.ui.timkiem.TimKiemActivity;
import com.framgia.capstone.ui.trangchinh.TrangChinhFragment;

import static com.framgia.capstone.utils.SharedPreferencesUtils.deletePhongKham;
import static com.framgia.capstone.utils.SharedPreferencesUtils.deleteUser;
import static com.framgia.capstone.utils.SharedPreferencesUtils.loadUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private String mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mUser = loadUser(this);
        Toast.makeText(this, "Xin chào" + " " + mUser, Toast.LENGTH_SHORT).show();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addFragment(new TrangChinhFragment(), R.string.title_trangchu);
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
}
