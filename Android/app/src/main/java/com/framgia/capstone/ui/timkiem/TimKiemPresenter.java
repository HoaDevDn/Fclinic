package com.framgia.capstone.ui.timkiem;

import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.ThuocDataSource;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public class TimKiemPresenter implements TimKiemContract.Presenter {

    private TimKiemContract.View mView;
    private ThuocDataSource mThuocRepository;

    public TimKiemPresenter(TimKiemContract.View view,
            ThuocDataSource thuocRepository) {
        mView = view;
        mThuocRepository = thuocRepository;
    }

    @Override
    public void start() {
        getThuoc();
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
}
