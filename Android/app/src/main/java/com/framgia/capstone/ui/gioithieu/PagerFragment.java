package com.framgia.capstone.ui.gioithieu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.capstone.R;

/**
 * Created by tri on 4/8/2017.
 */
public class PagerFragment extends Fragment {
    private String mTitle;
    private int mHinhAnh;

    public static PagerFragment newInstance(String title, int image) {
        PagerFragment fragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("abc", title);
        bundle.putInt("dfg", image);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_pager, container, false);
       // TextView textView = (TextView) view.findViewById(R.id.text_pager);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_pager);
        mTitle = getArguments().getString("abc");
        mHinhAnh = getArguments().getInt("dfg");
 //       textView.setText(mTitle);
        imageView.setImageResource(mHinhAnh);
        return view;
    }
}
