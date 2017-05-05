package com.framgia.capstone.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tri on 03/05/2017.
 */

public class ToaThuoc implements Serializable {
    private int mMaToaThuoc;
    private String mTenToa;
    private String mMoTa;
    private String mChiTiet;
    private String mTenUser;
    private List<CTToaThuoc> mCTToaThuocList;



    public String getTenUser() {
        return mTenUser;
    }

    public void setTenUser(String tenUser) {
        mTenUser = tenUser;
    }

    public ToaThuoc() {
    }

    public int getMaToaThuoc() {
        return mMaToaThuoc;
    }

    public void setMaToaThuoc(int maToaThuoc) {
        mMaToaThuoc = maToaThuoc;
    }

    public String getTenToa() {
        return mTenToa;
    }

    public void setTenToa(String tenToa) {
        mTenToa = tenToa;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        mMoTa = moTa;
    }

    public String getChiTiet() {
        return mChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        mChiTiet = chiTiet;
    }

    public List<CTToaThuoc> getCTToaThuocList() {
        return mCTToaThuocList;
    }

    public void setCTToaThuocList(List<CTToaThuoc> CTToaThuocList) {
        mCTToaThuocList = CTToaThuocList;
    }
}

