package com.framgia.capstone.data.resource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.resource.remote.BenhRemoteDataSource;
import java.util.List;

/**
 * Created by Age on 4/9/2017.
 */

public class BenhRepository implements BenhDataSource {
    private static BenhRepository sBenhRepository;
    private final BenhDataSource mBenhRemoteDataSource;

    public BenhRepository(BenhDataSource thuocRemoteDataSource) {
        mBenhRemoteDataSource = thuocRemoteDataSource;
    }

    public static BenhRepository getInstance(Context context) {
        if (sBenhRepository == null) {
            return new BenhRepository(BenhRemoteDataSource.getInstance());
        }
        return sBenhRepository;
    }

    @Override
    public void getBenh(@NonNull final CallBack callBack) {
        mBenhRemoteDataSource.getBenh(new BenhDataSource.CallBack() {
            @Override
            public void onLoaded(List<Benh> benh) {
                callBack.onLoaded(benh);
            }

            @Override
            public void onDataNotAvailable() {
                callBack.onDataNotAvailable();
            }
        });
    }
}
