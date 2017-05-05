package com.framgia.capstone.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.io.Serializable;

/**
 * Created by tri on 03/05/2017.
 */

public class NhacThuocRealm extends RealmObject {

    private int mId;
    private int mMatoa;
    private String mTime;
    private int mStatus;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMatoa() {
        return mMatoa;
    }

    public void setMatoa(int matoa) {
        mMatoa = matoa;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public NhacThuocRealm(int id, int matoa, String time) {
        mId = id;
        mMatoa = matoa;
        mTime = time;
    }

    public NhacThuocRealm() {
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
