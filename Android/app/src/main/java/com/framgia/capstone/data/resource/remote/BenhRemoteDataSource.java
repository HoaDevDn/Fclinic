package com.framgia.capstone.data.resource.remote;

import android.support.annotation.NonNull;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.resource.BenhDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 4/9/2017.
 */

public class BenhRemoteDataSource implements BenhDataSource {

    private static BenhRemoteDataSource sDataSource;

    public static BenhRemoteDataSource getInstance() {
        if (sDataSource == null) {
            sDataSource = new BenhRemoteDataSource();
        }
        return sDataSource;
    }

    @Override
    public void getBenh(@NonNull CallBack callBack) {
        List<Benh> list = new ArrayList<>();
        list.add(new Benh(1, "phát ban",
                "http://ehospital.vn/wp-content/uploads/2016/03/ehospital.vn-gout1.jpg",
                "Sụt cân không giải thích được", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(2, "sốt cao", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Sốt kéo dài", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(3, "côn trùng đốt", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Khó thở", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(4, "cảm lạnh", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua"
                        + "-benh-chet-nguoi-nao_9.jpg",
                "Khó thở", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(5, "thâm mắt", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Thay đổi thói quen đi tiêu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(6, "đau đầu", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Nhức đầu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(7, "đau dạ dày", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg",
                "Mất thị lực thoáng qua, mất kiểm soát vận động hoặc lời nói", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(8, "đau lưng", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Thay đổi thói quen đi tiêu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(9, "đau răng", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Sốt kéo dài", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(10, "cao huyết áp", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Khó thở", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(11, "cảm lạnh", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Thay đổi thói quen đi tiêu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(12, "viêm họng", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Sốt kéo dài", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(13, "nhiễm trùng", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Khó thở", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(14, "bị cắt", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Nhức đầu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(15, "vết thâm", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Thay đổi thói quen đi tiêu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(16, "bị bỏng", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg",
                "Mất thị lực thoáng qua, mất kiểm soát vận động hoặc lời nói", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(17, "Dị ứng", "http://khoahocphattrien.vn/Images/Uploaded/Share/2015/10/31/Ho-ra-mau-co-the-la-dau-hieu-cua-benh-chet-nguoi-nao_9.jpg", "Thay đổi thói quen đi tiêu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(18, "đau khớp xương", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Khó thở", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        list.add(new Benh(19, "Suyễn", "http://wru.edu.vn/wp-content/uploads/2016/11/xuongkhop.jpg", "Nhức đầu", "Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị Đây là cách điều trị "));
        callBack.onLoaded(list);
    }
}
