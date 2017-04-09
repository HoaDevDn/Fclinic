package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 09/04/2017.
 */
public class User implements Serializable {
    private String mName;
    private String mTenTaiKhoan;
    private String mMatKhau;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        mTenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return mMatKhau;
    }

    public void setMatKhau(String matKhau) {
        mMatKhau = matKhau;
    }
}
