package com.wake.recyclerView.adapter.cell;

import com.wake.recyclerView.viewholder.RVViewHolder;

/**
 * Created by wangke on 2018/11/15.
 */
public interface DataBinder<T> {
    void bindData(RVViewHolder var1, T var2);
}