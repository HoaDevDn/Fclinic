package com.framgia.capstone.ui.adapter;

import com.framgia.capstone.data.model.Benh;
import com.framgia.capstone.data.model.Thuoc;

public interface OnItemClickListener {
    public void onItemClick(Thuoc thuoc);
    public void onItemClick(Benh benh);
}
