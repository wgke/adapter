package com.wgke.adapter.cell;

/**
 * Created by wangke on 2018/11/15.
 */


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.wgke.viewholder.RVViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiCell<T> extends BaseCell {
    private T data;
    private DataBinder<T> dataBinder;

    public MultiCell(@LayoutRes int layoutId) {
        super(layoutId);
    }

    public MultiCell(@LayoutRes int layoutId, int spanSize) {
        super(layoutId, spanSize);
    }

    public MultiCell(@LayoutRes int layoutId, T data, DataBinder<T> dataBinder) {
        super(layoutId);
        this.data = data;
        this.dataBinder = dataBinder;
    }

    public MultiCell(@LayoutRes int layoutId, int spanSize, T data, DataBinder<T> dataBinder) {
        super(layoutId, spanSize);
        this.data = data;
        this.dataBinder = dataBinder;
    }

    @Nullable
    public T getData() {
        return this.data;
    }

    public final void onBindData(RVViewHolder viewHolder) {
        if(this.dataBinder != null) {
            this.dataBinder.bindData(viewHolder, this.data);
        }

    }

    public void setData(T data) {
        this.data = data;
    }

    public DataBinder<T> getDataBinder() {
        return this.dataBinder;
    }

    public void setDataBinder(DataBinder<T> dataBinder) {
        this.dataBinder = dataBinder;
    }

    public static <T> MultiCell<T> convert(int layoutRes, int spanSize, T bean, DataBinder<T> dataBinder) {
        return new MultiCell(layoutRes, spanSize, bean, dataBinder);
    }

    public static <T> MultiCell<T> convert(int layoutRes, T bean, DataBinder<T> dataBinder) {
        return new MultiCell(layoutRes, bean, dataBinder);
    }

    public static <T> List<MultiCell> convert(int layoutRes, List<T> beanList, DataBinder<T> dataBinder) {
        if(beanList == null) {
            return null;
        } else {
            List<MultiCell> multiBeanCellList = new ArrayList();
            Iterator var4 = beanList.iterator();

            while(var4.hasNext()) {
                T bean = (T) var4.next();
                multiBeanCellList.add(convert(layoutRes, bean, dataBinder));
            }

            return multiBeanCellList;
        }
    }

    public static <T> List<Cell> convert2(int layoutRes, List<T> beanList, DataBinder<T> dataBinder) {
        if(beanList == null) {
            return null;
        } else {
            List<Cell> multiBeanCellList = new ArrayList();
            Iterator var4 = beanList.iterator();

            while(var4.hasNext()) {
                T bean = (T) var4.next();
                multiBeanCellList.add(convert(layoutRes, bean, dataBinder));
            }

            return multiBeanCellList;
        }
    }
}
