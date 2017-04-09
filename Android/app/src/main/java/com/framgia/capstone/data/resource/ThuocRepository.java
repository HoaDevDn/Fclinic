package com.framgia.capstone.data.resource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.remote.ThuocRemoteDataSource;
import java.util.List;

/**
 * Created by Age on 3/23/2017.
 */
public class ThuocRepository implements ThuocDataSource {
    private static ThuocRepository sThuocRepository;
    private final ThuocDataSource mThuocRemoteDataSource;

    public ThuocRepository(ThuocDataSource thuocRemoteDataSource) {
        mThuocRemoteDataSource = thuocRemoteDataSource;
    }

    public static ThuocRepository getInstance(Context context) {
        if (sThuocRepository == null) {
            return new ThuocRepository(ThuocRemoteDataSource.getInstance());
        }
        return sThuocRepository;
    }

    @Override
    public void getThuoc(@NonNull final CallBack callback) {
        mThuocRemoteDataSource.getThuoc(new CallBack() {
            @Override
            public void onLoaded(List<Thuoc> thuoc) {
                callback.onLoaded(thuoc);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
