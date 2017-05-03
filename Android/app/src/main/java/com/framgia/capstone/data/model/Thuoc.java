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
    private String mMaVach;
    private String mMaHinh;
    private String mTacDung;
    private String mChongChiDinh;
    private String mTenLoaiThuoc;
    private int mStt;

    public Thuoc(int mMaThuoc, String mMaLoaiThuoc, String mTenThuoc, String mHinhAnh,
                 String mMoTa, String maVach, String maHinh, String chongChiDinh, String tenLoaiThuoc, float gia) {
        this.mMaThuoc = mMaThuoc;
        this.mMaLoaiThuoc = mMaLoaiThuoc;
        this.mTenThuoc = mTenThuoc;
        this.mHinhAnh = mHinhAnh;
        mTacDung = mMoTa;
        mMaVach=maVach;
        mMaHinh=maHinh;
        mChongChiDinh=chongChiDinh;
        mTenLoaiThuoc=tenLoaiThuoc;
        mGia=gia;
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

    public String getMaVach() {
        return mMaVach;
    }

    public void setMaVach(String maVach) {
        mMaVach = maVach;
    }

    public String getMaHinh() {
        return mMaHinh;
    }

    public void setMaHinh(String maHinh) {
        mMaHinh = maHinh;
    }

    public String getTacDung() {
        return mTacDung;
    }

    public void setTacDung(String tacDung) {
        mTacDung = tacDung;
    }

    public String getChongChiDinh() {
        return mChongChiDinh;
    }

    public void setChongChiDinh(String chongChiDinh) {
        mChongChiDinh = chongChiDinh;
    }

    public String getTenLoaiThuoc() {
        return mTenLoaiThuoc;
    }

    public void setTenLoaiThuoc(String tenLoaiThuoc) {
        mTenLoaiThuoc = tenLoaiThuoc;
    }

    public int getStt() {
        return mStt;
    }

    public void setStt(int stt) {
        mStt = stt;
    }
}
