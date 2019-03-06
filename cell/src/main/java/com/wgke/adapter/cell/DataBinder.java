package com.wgke.adapter.cell;

import com.wgke.viewholder.RVViewHolder;

/**
 * Created by wangke on 2018/11/15.
 */
public interface DataBinder<T> {
    void bindData(RVViewHolder var1, T var2);
}