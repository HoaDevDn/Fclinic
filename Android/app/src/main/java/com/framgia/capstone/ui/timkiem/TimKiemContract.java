package com.framgia.capstone.ui.timkiem;

import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.ui.BasePresenter;
import com.framgia.capstone.ui.BaseView;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public interface TimKiemContract {
    interface View extends BaseView<TimKiemContract.Presenter> {
        void showThuoc(List<Thuoc> list);

        void showBenh(List<Benh> list);

        void xayraLoi();
    }

    interface Presenter extends BasePresenter {
        void getThuoc();
    }
}
