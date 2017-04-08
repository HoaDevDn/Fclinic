package com.framgia.capstone.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.databinding.ItemThuocBinding;
import java.util.List;

/**
 * Created by Age on 4/8/2017.
 */

public class ThuocAdapter extends RecyclerView.Adapter<ThuocAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener;
    private List<Thuoc> mList;
    private LayoutInflater mLayoutInflater;
    private ItemThuocBinding mDataBinding;
    private Context mContext;

    public ThuocAdapter(Context Context, List<Thuoc> list) {
        mList = list;
        mContext = Context;
    }

    public void updateData(List<Thuoc> list) {
        mList.clear();
        if (list != null) mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        mDataBinding = ItemThuocBinding.inflate(mLayoutInflater, parent, false);
        return new ViewHolder(mDataBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Thuoc thuoc = mList.get(position);
        holder.bindData(thuoc);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemThuocBinding mDataBinding;

        public ViewHolder(ItemThuocBinding dataBinding) {
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

        public void bindData(Thuoc thuoc) {
            if (thuoc == null) return;
            mDataBinding.setItemview(thuoc);
        }
    }
}
