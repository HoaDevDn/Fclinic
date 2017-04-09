package com.framgia.capstone.ui.login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.framgia.capstone.R;
import com.framgia.capstone.ui.adapter.ViewPagerAdaterDangNhap;

public class LoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDangNhap);
        ViewPagerAdaterDangNhap adapterViewPagerDangNhap =
            new ViewPagerAdaterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerDangNhap);
        adapterViewPagerDangNhap.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }
}
