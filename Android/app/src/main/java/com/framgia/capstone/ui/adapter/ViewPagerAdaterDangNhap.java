package com.framgia.capstone.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.framgia.capstone.ui.login.dangky.FragmentDangKy;
import com.framgia.capstone.ui.login.dangnhap.FragmentDangNhap;

/**
 * Created by chaupham on 9/29/2016.
 */
public class ViewPagerAdaterDangNhap extends FragmentPagerAdapter {
    public ViewPagerAdaterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return fragmentDangKy;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng Ký";
            default:
                return null;
        }
    }
}
