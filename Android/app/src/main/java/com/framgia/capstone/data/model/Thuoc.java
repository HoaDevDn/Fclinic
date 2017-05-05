package com.framgia.capstone.data.model;

import io.realm.RealmObject;
import java.io.Serializable;

/**
 * Created by tri on 4/7/2017.
 */
public class Thuoc implements Serializable {
    private String mMaThuoc;
    private String mMaLoaiThuoc;
    private String mTenThuoc;
    private String mHinhAnh;
    private String mGia;
    private String mMaVach;
    private String mMaHinh;
    private String mTacDung;
    private String mChongChiDinh;
    private String mTenLoaiThuoc;
    private int mStt;

    public Thuoc() {
    }

    public Thuoc(String mMaThuoc, String mMaLoaiThuoc, String mTenThuoc, String mHinhAnh,
            String mMoTa, String maVach, String maHinh, String chongChiDinh, String tenLoaiThuoc, String gia) {
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

    public String getMaThuoc() {
        return mMaThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        mMaThuoc = maThuoc;
    }

    public String getGia() {
        return mGia;
    }

    public void setGia(String gia) {
        mGia = gia;
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
