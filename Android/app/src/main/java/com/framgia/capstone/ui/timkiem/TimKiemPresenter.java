package com.framgia.capstone.ui.timkiem;

import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.BenhDataSource;
import com.framgia.capstone.data.resource.ThuocDataSource;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public class TimKiemPresenter implements TimKiemContract.Presenter {

    private TimKiemContract.View mView;
    private ThuocDataSource mThuocRepository;
    private BenhDataSource mBenhRepository;

    public TimKiemPresenter(TimKiemContract.View view, ThuocDataSource thuocRepository,
            BenhDataSource benhDataSource) {
        mView = view;
        mThuocRepository = thuocRepository;
        mBenhRepository = benhDataSource;
    }

    @Override
    public void start() {
        getThuoc();
        getBenh();
    }

    @Override
    public void getThuoc() {
        mThuocRepository.getThuoc(new ThuocDataSource.CallBack() {
            @Override
            public void onLoaded(List<Thuoc> thuoc) {
                mView.showThuoc(thuoc);
            }

            @Override
            public void onDataNotAvailable() {
                mView.xayraLoi();
            }
        });
    }

    public void getBenh() {
        mBenhRepository.getBenh(new BenhDataSource.CallBack() {
            @Override
            public void onLoaded(List<Benh> benh) {
                mView.showBenh(benh);
            }

            @Override
            public void onDataNotAvailable() {
                mView.xayraLoi();
            }
        });
    }
}
