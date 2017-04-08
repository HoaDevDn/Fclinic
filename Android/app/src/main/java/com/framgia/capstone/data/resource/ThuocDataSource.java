package com.framgia.capstone.data.resource;

import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Thuoc;
import java.util.List;

/**
 * Created by Age on 3/23/2017.
 */
public interface ThuocDataSource {
    interface CallBack {

        void onLoaded(List<Thuoc> thuoc);

        void onDataNotAvailable();
    }

    void getThuoc(@NonNull CallBack callback);
}
