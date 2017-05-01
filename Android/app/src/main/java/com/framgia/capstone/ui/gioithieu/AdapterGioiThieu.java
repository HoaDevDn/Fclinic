package com.framgia.capstone.ui.gioithieu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.capstone.R;

/**
 * Created by tri on 4/8/2017.
 */
public class AdapterGioiThieu extends FragmentStatePagerAdapter {
    private Context mContext;
    protected static final String[] CONTENT = new String[]{
        "Đặt lịch khám",
        "Tìm kiếm thuốc, bệnh",
        "Nhắc uống thuốc",
        "Xem Toa Thuốc"
    };
    protected static final int[] IMAGE =
        new int[]{R.drawable.ic_datlich1, R.drawable.ic_timkiem, R
            .drawable.ic_nhacuongthuoc, R.drawable.ic_toathuoc};
    private int mCount = CONTENT.length;

    public AdapterGioiThieu(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment
            .newInstance(CONTENT[position], IMAGE[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
