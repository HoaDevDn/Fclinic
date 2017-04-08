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

    public int getMaThuoc() {
        return mMaThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        mMaThuoc = maThuoc;
    }

    public String getMaLoaiThuoc() {
        return mMaLoaiThuoc;
    }

    public void setMaLoaiThuoc(String maLoaiThuoc) {
        mMaLoaiThuoc = maLoaiThuoc;
    }

    public String getTenThuoc() {
        return mTenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        mTenThuoc = tenThuoc;
    }

    public String getHinhAnh() {
        return mHinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        mHinhAnh = hinhAnh;
    }

    public float getGia() {
        return mGia;
    }

    public void setGia(float gia) {
        mGia = gia;
    }

    public int getMaVach() {
        return mMaVach;
    }

    public void setMaVach(int maVach) {
        mMaVach = maVach;
    }

    public int getMaHinh() {
        return mMaHinh;
    }

    public void setMaHinh(int maHinh) {
        mMaHinh = maHinh;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        mMoTa = moTa;
    }
}
