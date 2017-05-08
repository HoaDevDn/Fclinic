package com.framgia.capstone.data.model;

import io.realm.RealmObject;
import java.io.Serializable;

/**
 * Created by tri on 08/05/2017.
 */

public class Status extends RealmObject implements Serializable {
    private int mTrangThai;
    private int mMaToa;

    public Status() {
    }

    public int getTrangThai() {
        return mTrangThai;
    }

    public void setTrangThai(int trangThai) {
        mTrangThai = trangThai;
    }

    public int getMaToa() {
        return mMaToa;
    }

    public void setMaToa(int maToa) {
        mMaToa = maToa;
    }
}
