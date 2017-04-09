package com.framgia.capstone.ui.login;

import com.framgia.capstone.ui.BasePresenter;
import com.framgia.capstone.ui.BaseView;
import com.framgia.capstone.ui.nhathuoc.NhaThuocConTract;

/**
 * Created by tri on 09/04/2017.
 */
public interface LoginContract {
    interface View extends BaseView<NhaThuocConTract.Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
