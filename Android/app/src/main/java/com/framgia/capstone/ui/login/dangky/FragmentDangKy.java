package com.framgia.capstone.ui.login.dangky;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.User;
import com.framgia.capstone.utils.JSONParser;
import com.framgia.capstone.utils.NetworkUtils;
import com.framgia.capstone.utils.RestAPI;
import com.framgia.capstone.widge.ClearEditText;
import com.framgia.capstone.widge.PasswordEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;

/**
 * Created by chaupham on 9/29/2016.
 */
public class FragmentDangKy extends Fragment implements View.OnClickListener {
    ClearEditText edTenTaiKhoanDK, edDiaChiEmailDK, HoTen;
    PasswordEditText edMatKhauDK, edNhapLaiMatKhauDK;
    Button btnDangKy;
    String ngayhientai;

    public FragmentDangKy() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);
        edTenTaiKhoanDK = (ClearEditText) view.findViewById(R.id.edTenTaiKhoanDK);
        edMatKhauDK = (PasswordEditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhauDK = (PasswordEditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmailDK = (ClearEditText) view.findViewById(R.id.edDiaChiEmailDK);
        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(this);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ngayhientai = dateFormat.format(cal.getTime());
        edTenTaiKhoanDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String taikhoan = edTenTaiKhoanDK.getText().toString();
                new AsyncKiemTraTK().execute(taikhoan);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDangKy) {
            User taikhoanDTO = new User();
            String taikhoan = edTenTaiKhoanDK.getText().toString();
            String matkhau = edMatKhauDK.getText().toString();
            String email = edDiaChiEmailDK.getText().toString();
            String nhaplaimatkhau = edNhapLaiMatKhauDK.getText().toString();
            taikhoanDTO.setTenTaiKhoan(taikhoan);
            taikhoanDTO.setMatKhau(matkhau);
            taikhoanDTO.setEmail(email);
            taikhoanDTO.setNgayTao(ngayhientai);
            NetworkUtils utils = new NetworkUtils(FragmentDangKy.this.getActivity());
            if (utils.isConnectingToInternet()) {
                if (edTenTaiKhoanDK.length() >= 3) {
                    if (edMatKhauDK.length() >= 3) {
                        if (nhaplaimatkhau.equals(matkhau)) {
                            new AsynThemTaiKhoan().execute(taikhoanDTO);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Mật khẩu không trùng khớp, vui lòng nhập lại !",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Mật khẩu ít hơn 3 kí tự, vui lòng nhập lại !", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Tài khoản ít hơn 3 kí tự, vui lòng nhập lại !", Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                Toast.makeText(getActivity(), "Kiểm tra kết nối mạng...!", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public class AsynThemTaiKhoan extends AsyncTask<User, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(User... params) {
            RestAPI api = new RestAPI();
            try {
                api.ThemTaiKhoan(params[0].getTenTaiKhoan(), params[0].getMatKhau(),
                        params[0].getEmail(), params[0].getNgayTao());
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang xử lý ...");
                progressDialog.show();
            }
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getActivity().getApplicationContext(), "Đăng kí thành công !",
                    Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }

    public class AsyncKiemTraTK extends AsyncTask<String, JSONObject, Boolean> {
        boolean kiemtra = false;
        String TenTK = null;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                JSONParser parser = new JSONParser();
                RestAPI api = new RestAPI();
                JSONObject jsonObj = api.KiemTraDK(params[0]);
                kiemtra = parser.KiemTraDK(jsonObj);
                TenTK = params[0];
            } catch (Exception e) {
                Log.d("LoiParser", e.getMessage());
            }
            return kiemtra;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(getActivity().getApplicationContext(), "Tên tài khoàn đã tồn tại !",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}




