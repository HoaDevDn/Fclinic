package com.framgia.capstone.ui.trangchinh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.capstone.R;
import com.framgia.capstone.ui.datlich.DatLichFragment;
import com.framgia.capstone.ui.gioithieu.GioiThieuFragment;

/**
 * Created by tri on 4/7/2017.
 */
public class TrangChinhFragment extends Fragment {
    public TrangChinhFragment() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchinh, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addFragment(GioiThieuFragment.newInstance());
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    addFragment(GioiThieuFragment.newInstance());
                    break;
                case R.id.navigation_datlich:
                    addFragment(DatLichFragment.newInstance());
                    break;
                case R.id.navigation_canhan:
                    //    Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                    break;
                default:
                   break;
            }
            BottomNavigationView navigation = (BottomNavigationView) getActivity().findViewById(R.id
                .navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            return true;
        }
    };

    public void addFragment(Fragment fragment) {
        addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,
            R.id.trang_chinh);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigation = (BottomNavigationView) getActivity().findViewById(R.id
            .navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addFragment(GioiThieuFragment.newInstance());
    }
}
