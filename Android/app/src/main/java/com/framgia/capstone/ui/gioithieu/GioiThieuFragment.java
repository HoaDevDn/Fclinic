package com.framgia.capstone.ui.gioithieu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.PhongKham;
import com.squareup.picasso.Picasso;

import static com.framgia.capstone.utils.SharedPreferencesUtils.loadPhongKham;

/**
 * Created by tri on 4/8/2017.
 */
public class GioiThieuFragment extends Fragment {
    @BindView(R.id.image_anhPK)
    ImageView mAnh;
    @BindView(R.id.text_tenPK)
    TextView mTenPK;
    @BindView(R.id.text_mota)
    TextView mMoTa;
    @BindView(R.id.text_diachi)
    TextView mDiaChi;
    @BindView(R.id.text_sdt)
    TextView mSdt;

    private PhongKham mPhongKham;

    public static GioiThieuFragment newInstance() {
        return new GioiThieuFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);
        ButterKnife.bind(this, view);
        mPhongKham = loadPhongKham(getActivity());
        if (mPhongKham == null) return null;

        Picasso.with(getActivity()).load(mPhongKham.getHinhAnh()).into(mAnh);
        mTenPK.setText(mPhongKham.getTenPhongKham());
        mMoTa.setText(mPhongKham.getMoTa());
        mDiaChi.setText(mPhongKham.getDiaChi());
        mSdt.setText(mPhongKham.getSDT());
        return view;
    }
}
