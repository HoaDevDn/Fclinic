package com.framgia.capstone.ui.login;

/**
 * Created by tri on 09/04/2017.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    @Override
    public void start() {
        mView.start();
    }
}
