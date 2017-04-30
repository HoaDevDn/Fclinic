package com.framgia.capstone.ui.datlich;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tri on 30/04/2017.
 */

public class ViewPagerDatLich extends FragmentStatePagerAdapter {

    public ViewPagerDatLich(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LichTrongFragment fragmentDangNhap = new LichTrongFragment();
                return fragmentDangNhap;
            case 1:
                LichDaDatFragment fragmentDangKy = new LichDaDatFragment();
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
                return "Lịch Còn Trống";
            case 1:
                return "Lịch Đã Đặt";
            default:
                return null;
        }
    }
}
