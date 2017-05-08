package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class CTToaThuoc implements Serializable {
    private int mMaToa;
    private String mMoTa;
    private String mSoLuong;
    private int mStt;
    private String mTenThuoc;

    public CTToaThuoc() {
    }


    public String getTenThuoc() {
        return mTenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        mTenThuoc = tenThuoc;
    }

    public int getMaToa() {
        return mMaToa;
    }

    public void setMaToa(int maToa) {
        mMaToa = maToa;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        mMoTa = moTa;
    }

    public String getSoLuong() {
        return mSoLuong;
    }

    public void setSoLuong(String soLuong) {
        mSoLuong = soLuong;
    }

    public int getStt() {
        return mStt;
    }

    public void setStt(int stt) {
        mStt = stt;
    }
}
