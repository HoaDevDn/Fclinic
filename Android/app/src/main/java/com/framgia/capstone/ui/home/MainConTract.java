package com.framgia.capstone.ui.home;

import com.framgia.capstone.ui.BasePresenter;
import com.framgia.capstone.ui.BaseView;
import com.framgia.capstone.ui.nhathuoc.NhaThuocConTract;

/**
 * Created by tri on 4/8/2017.
 */
public interface MainConTract {
    interface View extends BaseView<NhaThuocConTract.Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
