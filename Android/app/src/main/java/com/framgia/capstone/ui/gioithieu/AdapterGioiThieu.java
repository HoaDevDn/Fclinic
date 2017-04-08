package com.framgia.capstone.ui.gioithieu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framgia.capstone.R;

/**
 * Created by tri on 4/8/2017.
 */
public class AdapterGioiThieu extends FragmentPagerAdapter {
    protected static final String[] CONTENT = new String[]{
        " Chúng tôi cung cấp cho bạn các tiêu chuẩn cao nhất về chất lượng dịch vụ chăm sóc sức " +
            "khỏe được thiết kế để đáp ứng nhu cầu cụ thể của mọi thành viên trong gia đình bạn. Bạn có thể đặt ngay một lịch khám cho mình để đảm bảo cho sức khỏe của bạn",
        "Đây là chức năng tìm kiếm. Bạn có thể tìm kiếm bất cứ 1 hiệu thuốc nào ở gần bạn, hay cũng " +
            "có thể tìm kiếm được một số bệnh bạn có thể mắc phải khi bạn nhập triệu chứng của " +
            "bạn.",
        "Với ứng dụng này sau khi có bác sĩ cung cấp cho bạn một đơn thuốc. Bạn hãy yên tâm rằng sẽ " +
            "không bỏ lỡ viên với chức năng nhắc uống thuốc của ứng dụng.",
        "Bạn cũng có thể xem lại toa thuốc của mình chi tiết hơn sau khi bấm vào toa thuốc của bạn."
    };
    protected static final int[] IMAGE =
        new int[]{R.drawable.ic_datlich1, R.drawable.ic_timkiem, R
            .drawable.ic_nhacuongthuoc, R.drawable.ic_toathuoc};
    private int mCount = CONTENT.length;

    public AdapterGioiThieu(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment
            .newInstance(CONTENT[position % CONTENT.length], IMAGE[position % IMAGE.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return AdapterGioiThieu.CONTENT[position % CONTENT.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }

    public static final class TestFragment extends Fragment {
        private static final String KEY_CONTENT = "TestFragment:Content";

        public static TestFragment newInstance(String content) {
            TestFragment fragment = new TestFragment();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                builder.append(content).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            fragment.mContent = builder.toString();
            return fragment;
        }

        private String mContent = "???";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
                mContent = savedInstanceState.getString(KEY_CONTENT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TextView text = new TextView(getActivity());
            text.setGravity(Gravity.CENTER);
            text.setText(mContent);
            text.setTextSize(20 * getResources().getDisplayMetrics().density);
            text.setPadding(20, 20, 20, 20);
            LinearLayout layout = new LinearLayout(getActivity());
            layout.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
            layout.setGravity(Gravity.CENTER);
            layout.addView(text);
            return layout;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString(KEY_CONTENT, mContent);
        }
    }
}
