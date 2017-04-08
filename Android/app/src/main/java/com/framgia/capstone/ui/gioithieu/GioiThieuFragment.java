package com.framgia.capstone.ui.gioithieu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.capstone.R;
import com.framgia.capstone.utils.ZoomOutPageTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;

/**
 * Created by tri on 4/8/2017.
 */
public class GioiThieuFragment extends Fragment {
    AdapterGioiThieu mAdapter;
    ViewPager mPager;
    InkPageIndicator mIndicator;

    public static GioiThieuFragment newInstance() {
        return new GioiThieuFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);
        mAdapter = new AdapterGioiThieu(getActivity().getSupportFragmentManager());
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setAdapter(mAdapter);
        mIndicator = (InkPageIndicator) view.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        return view;
    }
}
