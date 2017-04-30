package com.framgia.capstone.ui.datlich;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.R;

public class DatLichFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    public DatLichFragment() {
    }

    public static DatLichFragment newInstance() {
        return new DatLichFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dat_lich, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_datlich);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_datlich);
        ViewPagerDatLich adapter = new ViewPagerDatLich(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
