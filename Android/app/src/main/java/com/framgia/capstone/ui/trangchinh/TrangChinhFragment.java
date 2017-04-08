package com.framgia.capstone.ui.trangchinh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.capstone.R;

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
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_datlich:
                    Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_canhan:
                    Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    break;
            }
            return false;
        }
    };
}
