package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class LichKham implements Serializable {
    private int mMa;
    private String mMota;
    private String mTgBatDau;
    private String mTgKetThuc;
    private String mNgay;
    private String mTrangThai;

    public LichKham() {
    }

    public LichKham(int ma, String mota, String tgBatDau, String tgKetThuc, String ngay,
            String trangThai) {
        mMa = ma;
        mMota = mota;
        mTgBatDau = tgBatDau;
        mTgKetThuc = tgKetThuc;
        mNgay = ngay;
        mTrangThai = trangThai;
    }

    public int getMa() {
        return mMa;
    }

    public void setMa(int ma) {
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
