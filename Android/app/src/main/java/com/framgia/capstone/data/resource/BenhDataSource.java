package com.framgia.capstone.data.resource;

import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import java.util.List;

/**
 * Created by Age on 4/9/2017.
 */

public interface BenhDataSource {
    interface CallBack {

        void onLoaded(List<Benh> benh);

        void onDataNotAvailable();
    }

    void getBenh(@NonNull CallBack callBack);
}
