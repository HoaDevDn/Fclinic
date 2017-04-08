package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 4/7/2017.
 */
public class LoaiThuoc implements Serializable {
    private String mMaLoaiThuoc;
    private String mTenLoaiThuoc;
    private String mHinhAnh;

    public LoaiThuoc(String mMaLoaiThuoc, String mTenLoaiThuoc, String mHinhAnh) {
        this.mMaLoaiThuoc = mMaLoaiThuoc;
        this.mTenLoaiThuoc = mTenLoaiThuoc;
        this.mHinhAnh = mHinhAnh;
    }

    public String getmMaLoaiThuoc() {
        return mMaLoaiThuoc;
    }

    public void setmMaLoaiThuoc(String mMaLoaiThuoc) {
        this.mMaLoaiThuoc = mMaLoaiThuoc;
    }

    public String getmTenLoaiThuoc() {
        return mTenLoaiThuoc;
    }

    public void setmTenLoaiThuoc(String mTenLoaiThuoc) {
        this.mTenLoaiThuoc = mTenLoaiThuoc;
    }

    public String getmHinhAnh() {
        return mHinhAnh;
    }

    public void setmHinhAnh(String mHinhAnh) {
        this.mHinhAnh = mHinhAnh;
    }
}
