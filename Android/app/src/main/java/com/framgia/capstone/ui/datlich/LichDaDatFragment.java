package com.framgia.capstone.ui.datlich;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.R;

/**
 * Created by tri on 30/04/2017.
 */

public class LichDaDatFragment extends Fragment {

    public LichDaDatFragment() {
    }

    public static LichDaDatFragment newInstance() {
        return new LichDaDatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lichdadat, container, false);

        return view;
    }
}
