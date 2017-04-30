package com.framgia.capstone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.databinding.ItemPhongkhamBinding;
import java.util.List;

/**
 * Created by Age on 4/30/2017.
 */

public class PhongKhamAdapter extends RecyclerView.Adapter<PhongKhamAdapter.ViewHolder> {
    private OnItemPhongKhamClickListener mItemClickListener;
    private List<PhongKham> mList;
    private LayoutInflater mLayoutInflater;
    private ItemPhongkhamBinding mDataBinding;
    private Context mContext;

    public PhongKhamAdapter(Context Context, List<PhongKham> list) {
        mList = list;
        mContext = Context;
    }

    public void updateData(List<PhongKham> list) {
        mList.clear();
        if (list != null) mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public PhongKhamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        mDataBinding = ItemPhongkhamBinding.inflate(mLayoutInflater, parent, false);
        return new PhongKhamAdapter.ViewHolder(mDataBinding);
    }

    @Override
    public void onBindViewHolder(PhongKhamAdapter.ViewHolder holder, int position) {
        PhongKham phongKham = mList.get(position);
        holder.bindData(phongKham);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(final OnItemPhongKhamClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemPhongkhamBinding mDataBinding;

        public ViewHolder(ItemPhongkhamBinding dataBinding) {
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

        public void bindData(PhongKham phongKham) {
            if (phongKham == null) return;
            mDataBinding.setItemview(phongKham);
        }
    }
}
