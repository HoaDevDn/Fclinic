package com.framgia.capstone.ui.toathuoc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.ToaThuoc;
import java.util.List;

/**
 * Created by tri on 03/05/2017.
 */

public class ToaThuocAdapter extends RecyclerView.Adapter<ToaThuocAdapter.ToaThuocViewHolder> {

    private List<ToaThuoc> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ItemClickListener mClickListener;

    public ToaThuocAdapter(Context context, List<ToaThuoc> list,
            ItemClickListener itemClickListener) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mClickListener = itemClickListener;
    }

    public void updateData(List<ToaThuoc> list) {
        if (list != null) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ToaThuocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_toathuoc, parent, false);
        return new ToaThuocViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToaThuocViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public interface ItemClickListener {
        void onClick(int position);
    }

    public class ToaThuocViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        @BindView(R.id.text_tenToa)
        TextView mTenToa;
        @BindView(R.id.text_tenUser)
        TextView mTenUser;
        @BindView(R.id.text_mota)
        TextView mMota;

        public ToaThuocViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(ToaThuoc toaThuoc) {
            mMota.setText(toaThuoc.getMoTa());
            mTenToa.setText(toaThuoc.getTenToa());
            mTenUser.setText(toaThuoc.getTenUser());
            itemView.startAnimation(AnimationUtils.loadAnimation(mContext.getApplicationContext(),
                    android.R.anim.slide_in_left));
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onClick(getAdapterPosition());
        }
    }
}
