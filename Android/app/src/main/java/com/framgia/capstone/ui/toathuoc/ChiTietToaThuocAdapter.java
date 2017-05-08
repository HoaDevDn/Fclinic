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
import com.framgia.capstone.data.model.CTToaThuoc;
import java.util.List;

/**
 * Created by tri on 03/05/2017.
 */

public class ChiTietToaThuocAdapter
        extends RecyclerView.Adapter<ChiTietToaThuocAdapter.ChiTietViewHolder> {

    private List<CTToaThuoc> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ItemClickListener mClickListener;

    public ChiTietToaThuocAdapter(Context context, List<CTToaThuoc> list,
            ItemClickListener itemClickListener) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mClickListener = itemClickListener;
    }

    public void updateData(List<CTToaThuoc> list) {
        if (list != null) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ChiTietViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_toa_thuoc, parent, false);
        return new ChiTietViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChiTietViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public interface ItemClickListener {
        void onClick(int position);
    }

    public class ChiTietViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_tenthuoc)
        TextView mTenThuoc;
        @BindView(R.id.text_mota)
        TextView mMota;
        @BindView(R.id.text_soluong)
        TextView mSoLuong;
        @BindView(R.id.text_stt)
        TextView mStt;

        public ChiTietViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(CTToaThuoc toaThuoc) {
            mTenThuoc.setText(toaThuoc.getTenThuoc());
            mMota.setText(toaThuoc.getMoTa());
            mSoLuong.setText(toaThuoc.getSoLuong());
            mStt.setText(getAdapterPosition() + 1 + "");
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onClick(getAdapterPosition());
        }
    }
}
