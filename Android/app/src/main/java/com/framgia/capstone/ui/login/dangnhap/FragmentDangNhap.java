package com.framgia.capstone.ui.login.dangnhap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.framgia.capstone.R;
import com.framgia.capstone.ui.chonphongkham.ChonPhongKhamActivity;
import com.framgia.capstone.ui.home.MainActivity;
import com.framgia.capstone.utils.JSONParser;
import com.framgia.capstone.utils.NetworkUtils;
import com.framgia.capstone.utils.RestAPI;
import com.framgia.capstone.widge.ClearEditText;
import com.framgia.capstone.widge.PasswordEditText;

import org.json.JSONObject;

import static com.framgia.capstone.R.id.btnDangNhap;
import static com.framgia.capstone.R.id.editTenTk;
import static com.framgia.capstone.utils.SharedPreferencesUtils.saveUser;

/**
 * Created by chaupham on 9/29/2016.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener {
    public FragmentDangNhap() {
    }

    ClearEditText taiKhoan;
    PasswordEditText matKhau;
    Button btnDangNhapFacebook, btnDangNhapGoogle, dangnhap;
    ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);
        taiKhoan = (ClearEditText) view.findViewById(editTenTk);
        matKhau = (PasswordEditText) view.findViewById(R.id.editMatKhau);
        dangnhap = (Button) view.findViewById(btnDangNhap);
        dangnhap.setOnClickListener(this);
        btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangNhapGoogle);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDangNhap) {
            String taikhoan = taiKhoan.getText().toString();
            String matkhau = matKhau.getText().toString();
            NetworkUtils utils = new NetworkUtils(FragmentDangNhap.this.getActivity());
            if (utils.isConnectingToInternet()) {
                new AsyncDangNhap().execute(taikhoan, matkhau);
            } else {
                Toast.makeText(getActivity(), "Kiểm tra kết nối mạng1...!", Toast.LENGTH_SHORT)
                    .show();
            }
        }
    }

    public class AsyncDangNhap extends AsyncTask<String, JSONObject, Boolean> {
        boolean kiemtra = false;
        String TenTK = null;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang kiểm tra...");
                progressDialog.show();
            }
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                JSONParser parser = new JSONParser();
                RestAPI api = new RestAPI();
                JSONObject jsonObj = api.KiemTraDangNhap(params[0], params[1]);
                kiemtra = parser.KiemTraDangNhap(jsonObj);
                TenTK = params[0];
            } catch (Exception e) {
                Log.d("LoiParser", e.getMessage());
            }
            return kiemtra;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                saveUser(getActivity(), taiKhoan.getText().toString());
                Intent i = new Intent(getActivity(), ChonPhongKhamActivity.class);
                startActivity(i);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "Sai tên tài khoản hoặc mật khẩu !",
                    Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        }
    }
}

