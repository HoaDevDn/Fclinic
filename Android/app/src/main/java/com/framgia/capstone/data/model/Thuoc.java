package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 4/7/2017.
 */
public class Thuoc implements Serializable {
    private int mMaThuoc;
    private String mMaLoaiThuoc;
    private String mTenThuoc;
    private String mHinhAnh;
    private float mGia;
    private int mMaVach;
    private int mMaHinh;
    private String mMoTa;

    public Thuoc(int mMaThuoc, String mMaLoaiThuoc, String mTenThuoc, String mHinhAnh,
                 String mMoTa) {
        this.mMaThuoc = mMaThuoc;
        this.mMaLoaiThuoc = mMaLoaiThuoc;
        this.mTenThuoc = mTenThuoc;
        this.mHinhAnh = mHinhAnh;
        this.mMoTa = mMoTa;
    }

    public String getmMoTa() {
        return mMoTa;
    }

    public void setmMoTa(String mMoTa) {
        this.mMoTa = mMoTa;
    }

    public int getmMaThuoc() {
        return mMaThuoc;
    }

    public void setmMaThuoc(int mMaThuoc) {
        this.mMaThuoc = mMaThuoc;
    }

    public String getmMaLoaiThuoc() {
        return mMaLoaiThuoc;
    }

    public void setmMaLoaiThuoc(String mMaLoaiThuoc) {
        this.mMaLoaiThuoc = mMaLoaiThuoc;
    }

    public String getmTenThuoc() {
        return mTenThuoc;
    }

    public void setmTenThuoc(String mTenThuoc) {
        this.mTenThuoc = mTenThuoc;
    }

    public String getmHinhAnh() {
        return mHinhAnh;
    }

    public void setmHinhAnh(String mHinhAnh) {
        this.mHinhAnh = mHinhAnh;
    }

    public float getmGia() {
        return mGia;
    }

    public void setmGia(float mGia) {
        this.mGia = mGia;
    }

    public int getmMaVach() {
        return mMaVach;
    }

    public void setmMaVach(int mMaVach) {
        this.mMaVach = mMaVach;
    }

    public int getmMaHinh() {
        return mMaHinh;
    }

    public void setmMaHinh(int mMaHinh) {
        this.mMaHinh = mMaHinh;
    }
}
