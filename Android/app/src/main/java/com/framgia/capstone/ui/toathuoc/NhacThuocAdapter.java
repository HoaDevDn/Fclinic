package com.framgia.capstone.ui.toathuoc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.NhacThuoc;
import java.util.List;

/**
 * Created by tri on 03/05/2017.
 */

public class NhacThuocAdapter extends RecyclerView.Adapter<NhacThuocAdapter.TimeViewHolder> {

    private List<NhacThuoc> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public NhacThuocAdapter(Context context, List<NhacThuoc> list) {
        mList = list;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void updateData(List<NhacThuoc> list) {
        if (list != null) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_time, parent, false);
        return new TimeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_time_toa)
        TextView mTime;

        public TimeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(NhacThuoc nhacThuoc) {
            mTime.setText(nhacThuoc.getTime());
        }
    }
}

