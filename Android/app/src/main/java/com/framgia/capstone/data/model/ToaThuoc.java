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
    private int mMaPK;
    private String mChiTiet;
    private String mTenUser;
    private List<CTToaThuoc> mCTToaThuocList;

    public ToaThuoc(int maToaThuoc, String tenToa, String moTa, int maPK, String chiTiet,
            String tenUser, List<CTToaThuoc> CTToaThuocList) {
        mMaToaThuoc = maToaThuoc;
        mTenToa = tenToa;
        mMoTa = moTa;
        mMaPK = maPK;
        mChiTiet = chiTiet;
        mTenUser = tenUser;
        mCTToaThuocList = CTToaThuocList;
    }

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

    public int getMaPK() {
        return mMaPK;
    }

    public void setMaPK(int maPK) {
        mMaPK = maPK;
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

