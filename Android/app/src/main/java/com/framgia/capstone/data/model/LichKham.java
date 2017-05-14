package com.framgia.capstone.data.model;

import android.text.TextUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

    private String mStart;
    private String mEnd;

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

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
        return convertUiFormatToDataFormat(mTgBatDau, INPUT_TIME_FORMAT, OUTPUT_TIME_FORMAT);
    }

    public void setTgBatDau(String tgBatDau) {
        mTgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
       return convertUiFormatToDataFormat(mTgKetThuc, INPUT_TIME_FORMAT, OUTPUT_TIME_FORMAT);
    }

    public void setTgKetThuc(String tgKetThuc) {
        mTgKetThuc = tgKetThuc;
    }

    public String getNgay() {
        return convertUiFormatToDataFormat(mNgay, INPUT_TIME_FORMAT, OUTPUT_DATE_FORMAT);
    }

    public void setNgay(String ngay) {
        mNgay = ngay;
    }

    public static String convertUiFormatToDataFormat(String date, String inputFormat,
            String outputFormat) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        SimpleDateFormat newSdf = new SimpleDateFormat(outputFormat, Locale.ENGLISH);
        try {
            return newSdf.format(sdf.parse(date));
        } catch (ParseException e) {
            return null;
        }
    }

    public static final String INPUT_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss";
    public static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String INPUT_TIME_FORMAT1 = "MM/dd/yyyy hh:mm:ss aa";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm";


}
