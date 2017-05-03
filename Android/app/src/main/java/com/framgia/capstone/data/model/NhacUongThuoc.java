package com.framgia.capstone.data.model;

import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class NhacUongThuoc implements Serializable {
    private int mMaToaThuoc;
    private String mTime;

    public NhacUongThuoc(int maToaThuoc, String time) {
        mMaToaThuoc = maToaThuoc;
        mTime = time;
    }

    public NhacUongThuoc() {
    }

    public int getMaToaThuoc() {
        return mMaToaThuoc;
    }

    public void setMaToaThuoc(int maToaThuoc) {
        mMaToaThuoc = maToaThuoc;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
