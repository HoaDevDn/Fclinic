package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class CTToaThuoc implements Serializable {
    private int mChiTiet;
    private int mMaThuoc;
    private String mMoTa;
    private int mSoLuong;

    public CTToaThuoc() {
    }

    public CTToaThuoc(int chiTiet, int maThuoc, String moTa, int soLuong) {
        mChiTiet = chiTiet;
        mMaThuoc = maThuoc;
        mMoTa = moTa;
        mSoLuong = soLuong;
    }

    public int getChiTiet() {
        return mChiTiet;
    }

    public void setChiTiet(int chiTiet) {
        mChiTiet = chiTiet;
    }

    public int getMaThuoc() {
        return mMaThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        mMaThuoc = maThuoc;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        mMoTa = moTa;
    }

    public int getSoLuong() {
        return mSoLuong;
    }

    public void setSoLuong(int soLuong) {
        mSoLuong = soLuong;
    }
}
