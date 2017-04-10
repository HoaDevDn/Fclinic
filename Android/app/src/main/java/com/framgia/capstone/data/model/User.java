package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 09/04/2017.
 */
public class User implements Serializable {
    private int mMaTaiKhoan;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mEmail;
    private String mNgayTao;

    public User() {
        this.mMaTaiKhoan = 0;
        this.mTenTaiKhoan = null;
        this.mMatKhau = null;
        this.mEmail = null;
        this.mNgayTao = null;
    }

    public int getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        mMaTaiKhoan = maTaiKhoan;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getNgayTao() {
        return mNgayTao;
    }

    public void setNgayTao(String ngayTao) {
        mNgayTao = ngayTao;
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
