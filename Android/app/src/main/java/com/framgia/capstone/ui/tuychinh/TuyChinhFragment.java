package com.framgia.capstone.ui.tuychinh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.capstone.R;

/**
 * Created by tri on 4/7/2017.
 */
public class TuyChinhFragment extends Fragment {
    public TuyChinhFragment() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuychinh, container, false);
        return view;
    }
}
