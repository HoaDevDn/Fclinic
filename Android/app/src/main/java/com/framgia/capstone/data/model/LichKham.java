package com.framgia.capstone.data.model;

import io.realm.RealmObject;
import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class LichKham implements Serializable {
    private String mMa;
    private String mMota;
    private String mTgBatDau;
    private String mTgKetThuc;
    private String mNgay;
    private String mTrangThai;
    private String mTime;
    private String mTenPK;
    private String mTenTK;
    private int mMaPk;

    public String getTenTK() {
        return mTenTK;
    }

    public void setTenTK(String tenTK) {
        mTenTK = tenTK;
    }

    public int getMaPk() {
        return mMaPk;
    }

    public void setMaPk(int maPk) {
        mMaPk = maPk;
    }

    public LichKham() {
    }

    public String getTenPK() {
        return mTenPK;
    }

    public void setTenPK(String tenPK) {
        mTenPK = tenPK;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getMa() {
        return mMa;
    }

    public void setMa(String ma) {
        mMa = ma;
    }

    public String getMota() {
        return mMota;
    }

    public void setMota(String mota) {
        mMota = mota;
    }

    public String getTgBatDau() {
        return mTgBatDau;
    }

    public void setTgBatDau(String tgBatDau) {
        mTgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
        return mTgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        mTgKetThuc = tgKetThuc;
    }

    public String getNgay() {
        return mNgay;
    }

    public void setNgay(String ngay) {
        mNgay = ngay;
    }

    public String getTrangThai() {
        return mTrangThai;
    }

    public void setTrangThai(String trangThai) {
        mTrangThai = trangThai;
    }
}
