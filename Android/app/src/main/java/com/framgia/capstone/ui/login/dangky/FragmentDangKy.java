package com.framgia.capstone.ui.login.dangky;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.capstone.R;

/**
 * Created by chaupham on 9/29/2016.
 */
public class FragmentDangKy extends Fragment {
    public FragmentDangKy(){}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);
        Toast.makeText(getActivity(),"dang ky",Toast.LENGTH_SHORT).show();
        return view;
    }
}




