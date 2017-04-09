package com.framgia.capstone.data.resource.remote;

import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.resource.ThuocDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public class ThuocRemoteDataSource implements ThuocDataSource {
    private static ThuocRemoteDataSource sDataSource;

    public static ThuocRemoteDataSource getInstance() {
        if (sDataSource == null) {
            sDataSource = new ThuocRemoteDataSource();
        }
        return sDataSource;
    }

    @Override
    public void getThuoc(@NonNull CallBack callback) {
        List<Thuoc> list=new ArrayList<>();
        list.add(new Thuoc(1,"1","Levothyroxine huy","http://www.chuatriviemdaitrang.com/wp-content/uploads/2016/08/thuoc-1.jpg","Hormone Thyroid"));
        list.add(new Thuoc(2,"2","Memantine","http://www.viemgan.com.vn/js/libs/kcfinder/upload/images/thuoc-gay-hai-cho-gan.jpg","Alzheimers"));
        list.add(new Thuoc(3,"3","Donepezil","http://tamlyhoctoipham.com/uploads/images/luu-y-khi-dung-thuoc-ha-huyet-ap-1.jpg","Alzheimers"));
        list.add(new Thuoc(4,"1","Zolpidem","http://mediplantex.com/Uploads/images/42/2013/12/12/business.jpg","An thần"));
        list.add(new Thuoc(5,"2","Eszopiclone","http://thoibaotaichinhvietnam.vn/Pictures102015/nguyenthiphuong-tbtc/thuocngoai.jpg","An thần"));
        list.add(new Thuoc(6,"2","Temazepam","http://www.yeutretho.vn/files/2015/10/31/14-sai-lam-te-hai-khi-uong-thuoc-khien-benh-them-nang-7.jpg","An thần"));
        list.add(new Thuoc(7,"3","Ticagrelor","http://thoatvidiadem.net/wp-content/uploads/thuoc-ho-tro-dieu-tri-thoai-hoa-cot-song.jpg","Bệnh tim mạch"));
        list.add(new Thuoc(8,"3","Etonogestrel +","https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQFdVNRvDIeMhHyryZXzA1zp1VjtcUkI1on-gwFFaIvFJIzILHi","Biện pháp tránh thai"));
        list.add(new Thuoc(9,"1","Folic Acid","http://giaoduc.net.vn/Uploaded/phamlieu/2013_05_15/luu-y-khi-uong-thuoc-giaoduc.net.vn2.jpg","Bổ sung acid folic"));
        list.add(new Thuoc(10,"3","Testosterone","http://baohaiphong.com.vn/dataimages/201511/original/images1200436_sleepingpills.jpg","Bổ sung vitamin D"));
        list.add(new Thuoc(11,"1","Levothyroxine","http://www.chuatriviemdaitrang.com/wp-content/uploads/2016/08/thuoc-1.jpg","Hormone Thyroid"));
        list.add(new Thuoc(12,"2","Memantine","http://www.viemgan.com.vn/js/libs/kcfinder/upload/images/thuoc-gay-hai-cho-gan.jpg","Alzheimers"));
        list.add(new Thuoc(13,"3","Donepezil","http://tamlyhoctoipham.com/uploads/images/luu-y-khi-dung-thuoc-ha-huyet-ap-1.jpg","Alzheimers"));
        list.add(new Thuoc(14,"1","Zolpidem","http://mediplantex.com/Uploads/images/42/2013/12/12/business.jpg","An thần"));
        list.add(new Thuoc(15,"2","Eszopiclone","http://thoibaotaichinhvietnam.vn/Pictures102015/nguyenthiphuong-tbtc/thuocngoai.jpg","An thần"));
        list.add(new Thuoc(16,"2","Temazepam","http://www.yeutretho.vn/files/2015/10/31/14-sai-lam-te-hai-khi-uong-thuoc-khien-benh-them-nang-7.jpg","An thần"));
        list.add(new Thuoc(17,"3","Ticagrelor","http://thoatvidiadem.net/wp-content/uploads/thuoc-ho-tro-dieu-tri-thoai-hoa-cot-song.jpg","Bệnh tim mạch"));
        list.add(new Thuoc(18,"3","Etonogestrel +","https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQFdVNRvDIeMhHyryZXzA1zp1VjtcUkI1on-gwFFaIvFJIzILHi","Biện pháp tránh thai"));
        list.add(new Thuoc(19,"1","Folic Acid","http://giaoduc.net.vn/Uploaded/phamlieu/2013_05_15/luu-y-khi-uong-thuoc-giaoduc.net.vn2.jpg","Bổ sung acid folic"));
        list.add(new Thuoc(20,"3","Testosterone","http://baohaiphong.com.vn/dataimages/201511/original/images1200436_sleepingpills.jpg","Bổ sung vitamin D"));
        list.add(new Thuoc(21,"1","Levothyroxine","http://www.chuatriviemdaitrang.com/wp-content/uploads/2016/08/thuoc-1.jpg","Hormone Thyroid"));
        list.add(new Thuoc(22,"2","Memantine","http://www.viemgan.com.vn/js/libs/kcfinder/upload/images/thuoc-gay-hai-cho-gan.jpg","Alzheimers"));
        list.add(new Thuoc(23,"3","Donepezil","http://tamlyhoctoipham.com/uploads/images/luu-y-khi-dung-thuoc-ha-huyet-ap-1.jpg","Alzheimers"));
        list.add(new Thuoc(24,"1","Zolpidem","http://mediplantex.com/Uploads/images/42/2013/12/12/business.jpg","An thần"));
        list.add(new Thuoc(25,"2","Eszopiclone","http://thoibaotaichinhvietnam.vn/Pictures102015/nguyenthiphuong-tbtc/thuocngoai.jpg","An thần"));
        list.add(new Thuoc(26,"2","Temazepam","http://www.yeutretho.vn/files/2015/10/31/14-sai-lam-te-hai-khi-uong-thuoc-khien-benh-them-nang-7.jpg","An thần"));
        list.add(new Thuoc(27,"3","Ticagrelor","http://thoatvidiadem.net/wp-content/uploads/thuoc-ho-tro-dieu-tri-thoai-hoa-cot-song.jpg","Bệnh tim mạch"));
        list.add(new Thuoc(28,"3","Etonogestrel +","https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQFdVNRvDIeMhHyryZXzA1zp1VjtcUkI1on-gwFFaIvFJIzILHi","Biện pháp tránh thai"));
        list.add(new Thuoc(29,"1","Folic Acid","http://giaoduc.net.vn/Uploaded/phamlieu/2013_05_15/luu-y-khi-uong-thuoc-giaoduc.net.vn2.jpg","Bổ sung acid folic"));
        list.add(new Thuoc(30,"3","Testosterone","http://baohaiphong.com.vn/dataimages/201511/original/images1200436_sleepingpills.jpg","Bổ sung vitamin D"));
        callback.onLoaded(list);
    }
}
