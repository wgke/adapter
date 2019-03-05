package com.wake.recyclerView.adapter.cell;


import com.wake.recyclerView.viewholder.RVViewHolder;

/**
 * Created by wangke on 2018/11/15.
 */

public interface OnAttachStateChangeListener {
    void onViewDetachedFromWindow(RVViewHolder var1);

    void onViewAttachedToWindow(RVViewHolder var1);
}
