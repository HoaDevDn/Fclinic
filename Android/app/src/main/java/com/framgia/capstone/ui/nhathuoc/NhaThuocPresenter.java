package com.framgia.capstone.ui.nhathuoc;

/**
 * Created by tri on 4/7/2017.
 */
public class NhaThuocPresenter implements NhaThuocConTract.Presenter {
    private NhaThuocConTract.View mView;
    @Override
    public void start() {
        mView.start();
    }
}
