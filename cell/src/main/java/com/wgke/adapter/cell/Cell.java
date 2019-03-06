package com.wgke.adapter.cell;

/**
 * Created by wangke on 2018/11/15.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.wgke.adapter.cell.ob.CellObserver;
import com.wgke.viewholder.RVViewHolder;

public interface Cell {
    int FULL_SPAN = -1;

    @Nullable
    RVViewHolder getAttachedViewHolder();

    void addTag(String var1, Object var2);

    @Nullable
    <T> T getTag(String var1);

    void onViewAttachedToWindow(RVViewHolder var1);

    void onViewDetachedFromWindow(RVViewHolder var1);

    void onBindData(RVViewHolder var1);

    void onViewCreated(RVViewHolder var1);

    @LayoutRes
    int getLayoutId();

    int getSpanSize();

    void registerCellObserver(CellObserver var1);

    void unRegisterCellObserver(CellObserver var1);

    void addOnAttachStateChangeListener(OnAttachStateChangeListener var1);

    void removeOnAttachStateChangeListener(OnAttachStateChangeListener var1);
}