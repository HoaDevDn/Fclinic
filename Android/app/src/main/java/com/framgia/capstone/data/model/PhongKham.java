package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by Age on 4/30/2017.
 */

public class PhongKham implements Serializable {
    private int mMaPhongKham;
    private String mTenPhongKham;
    private String mMoTa;
    private String mHinhAnh;
    private String mDiaChi;
    private String mSDT;

    public PhongKham() {
    }

    public PhongKham(int maPhongKham, String tenPhongKham, String moTa, String hinhAnh,
            String diaChi, String SDT) {
        mMaPhongKham = maPhongKham;
        mTenPhongKham = tenPhongKham;
        mMoTa = moTa;
        mHinhAnh = hinhAnh;
        mDiaChi = diaChi;
        mSDT = SDT;
    }

    public int getMaPhongKham() {
        return mMaPhongKham;
    }

    public void setMaPhongKham(int maPhongKham) {
        mMaPhongKham = maPhongKham;
    }

    public String getTenPhongKham() {
        return mTenPhongKham;
    }

    public void setTenPhongKham(String tenPhongKham) {
        mTenPhongKham = tenPhongKham;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        mMoTa = moTa;
    }

    public String getHinhAnh() {
        return mHinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        mHinhAnh = hinhAnh;
    }

    public String getDiaChi() {
        return mDiaChi;
    }

    public void setDiaChi(String diaChi) {
        mDiaChi = diaChi;
    }

    public String getSDT() {
        return mSDT;
    }

    public void setSDT(String SDT) {
        mSDT = SDT;
    }
}
