package com.framgia.capstone.ui.gioithieu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.framgia.capstone.R;

/**
 * Created by tri on 4/8/2017.
 */
public class GioiThieuFragment extends Fragment {
    @BindView(R.id.image_anhPK)
    TextView mAnh;
    @BindView(R.id.text_tenPK)
    TextView mTenPK;
    @BindView(R.id.text_mota)
    TextView mMoTa;
    @BindView(R.id.text_diachi)
    TextView mDiaChi;
    @BindView(R.id.text_sdt)
    TextView mSdt;

    public static GioiThieuFragment newInstance() {
        return new GioiThieuFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);
        ButterKnife.bind(getActivity());
        return view;
    }
}
