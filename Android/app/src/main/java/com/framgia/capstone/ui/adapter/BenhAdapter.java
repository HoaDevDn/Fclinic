package com.framgia.capstone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.databinding.ItemBenhBinding;
import java.util.List;

/**
 * Created by Age on 4/9/2017.
 */

public class BenhAdapter extends RecyclerView.Adapter<BenhAdapter.ViewHolder> {
    private OnItemBenhClickListener mItemClickListener;
    private List<Benh> mList;
    private LayoutInflater mLayoutInflater;
    private ItemBenhBinding mDataBinding;
    private Context mContext;

    public BenhAdapter(Context Context, List<Benh> list) {
        mList = list;
        mContext = Context;
    }

    public void updateData(List<Benh> list) {
        mList.clear();
        if (list != null) mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public BenhAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        mDataBinding = ItemBenhBinding.inflate(mLayoutInflater, parent, false);
        return new BenhAdapter.ViewHolder(mDataBinding);
    }

    @Override
    public void onBindViewHolder(BenhAdapter.ViewHolder holder, int position) {
        Benh benh = mList.get(position);
        holder.bindData(benh);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(final OnItemBenhClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemBenhBinding mDataBinding;

        public ViewHolder(ItemBenhBinding dataBinding) {
            super(dataBinding.getRoot());
            mDataBinding = dataBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mList.get(getPosition()));
            }
        }

        public void bindData(Benh benh) {
            if (benh == null) return;
            mDataBinding.setItemview(benh);
        }
    }
}
