package com.framgia.capstone.ui.login.dangnhap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.framgia.capstone.R;
import com.framgia.capstone.ui.gioithieu.GioiThieuFragment;
import com.framgia.capstone.widge.ClearEditText;
import com.framgia.capstone.widge.PasswordEditText;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.framgia.capstone.R.id.btnDangNhap;
import static com.framgia.capstone.R.id.editTenTk;

/**
 * Created by chaupham on 9/29/2016.
 */
public class FragmentDangNhap extends Fragment {
    public FragmentDangNhap() {
    }

    ClearEditText taiKhoan;
    PasswordEditText matKhau;
    Button btnDangNhapFacebook, btnDangNhapGoogle, dangnhap;
    ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);
        taiKhoan = (ClearEditText)  view.findViewById(editTenTk);
        matKhau = (PasswordEditText)view.findViewById(R.id.editMatKhau);
        dangnhap = (Button) view.findViewById(btnDangNhap);

        btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangNhapGoogle);

        Toast.makeText(getActivity(),"dang nhap",Toast.LENGTH_SHORT).show();

        return view;
    }
}

