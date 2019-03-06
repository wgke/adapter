package com.wgke.adapter;

/**
 * Created by wangke on 2018/11/15.
 */


import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseObjectRecyclerAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerHeaderFooterAdapter<V> {
    private final List<T> dataList;
    protected Context context;

    public BaseObjectRecyclerAdapter(Context context) {
        this(context, (List)null);
    }

    public BaseObjectRecyclerAdapter(Context context, List<T> dataList) {
        this.dataList = new ArrayList();
        if(dataList != null) {
            this.dataList.addAll(dataList);
        }

        this.context = context;
    }

    public int getContentItemCount() {
        return this.dataList.size();
    }

    public void addDataAtIndex(T data, int position) {
        this.dataList.add(position, data);
        int startPosition = this.getFixedPosition(position);
        this.notifyItemInserted(startPosition);
        this.notifyItemRangeChangedIfNeed(position);
    }

    public void addDataListAtIndex(List<T> dataList, int position) {
        if(dataList != null && !dataList.isEmpty()) {
            this.dataList.addAll(position, dataList);
            int startPosition = this.getFixedPosition(position);
            this.notifyItemRangeInserted(startPosition, dataList.size());
            this.notifyItemRangeChangedIfNeed(position);
        }
    }

    public void removeDataAtIndex(int position) {
        if(position >= 0 && position <= this.getContentItemCount() - 1) {
            this.dataList.remove(position);
            int startPosition = this.getFixedPosition(position);
            this.notifyItemRemoved(startPosition);
            this.notifyItemRangeChangedIfNeed(position);
        }
    }

    public int getDataIndex(T data) {
        return data == null?-1:this.dataList.indexOf(data);
    }

    public void addDataAtLast(T data) {
        this.addDataAtIndex(data, this.dataList.size());
    }

    public boolean isEmpty() {
        return this.dataList != null && this.dataList.isEmpty();
    }

    public void addDataListAtLast(List<T> dataList) {
        this.addDataListAtIndex(dataList, this.dataList.size());
    }

    public void addDataAtFirst(T data) {
        this.addDataAtIndex(data, 0);
    }

    public void addDataListAtFirst(List<T> dataList) {
        this.addDataListAtIndex(dataList, 0);
    }

    public void removeData(T data) {
        this.removeDataAtIndex(this.getDataIndex(data));
    }

    public void removeDataFrom(int position, int count) {
        if(count > 0 && position >= 0 && position + count <= this.getContentItemCount()) {
            int index = -1;
            Iterator iterator = this.dataList.iterator();

            while(iterator.hasNext()) {
                iterator.next();
                ++index;
                if(index >= position && index < position + count) {
                    iterator.remove();
                }

                if(index == position + count) {
                    break;
                }
            }

            int startPosition = this.getFixedPosition(position);
            this.notifyItemRangeRemoved(startPosition, count);
            this.notifyItemRangeChangedIfNeed(position);
        }
    }

    public void removeDataFrom(int position) {
        this.removeDataFrom(position, this.getContentItemCount() - position);
    }

    public void removeAll() {
        this.dataList.clear();
        this.notifyDataSetChanged();
    }

    public void setDataList(List<T> dataList) {
        this.dataList.clear();
        if(dataList != null) {
            this.dataList.addAll(dataList);
        }

        this.notifyDataSetChanged();
    }

    public T getDataAt(int position) {
        return this.dataList.get(position);
    }

    public List<T> getDataList() {
        return this.dataList;
    }

    public void swap(int fromPosition, int toPosition) {
        Collections.swap(this.dataList, fromPosition, toPosition);
        this.notifyItemMoved(this.getFixedPosition(fromPosition), this.getFixedPosition(toPosition));
    }

    public void replace(int position, T data) {
        if(position >= 0 && position < this.dataList.size()) {
            this.dataList.set(position, data);
            this.notifyItemChanged(this.getFixedPosition(position));
        }
    }

    public void replace(int position, List<T> dataList) {
        if(position >= 0 && position < this.dataList.size()) {
            if(dataList != null && !dataList.isEmpty()) {
                int dataSize = dataList.size();
                if(dataSize == 1) {
                    this.replace(position, dataList.get(0));
                } else {
                    this.dataList.remove(position);
                    this.notifyItemRemoved(this.getFixedPosition(position));
                    this.dataList.addAll(position, dataList);
                    this.notifyItemRangeInserted(this.getFixedPosition(position), dataSize);
                    this.notifyItemRangeChangedIfNeed(position + dataSize);
                }
            }
        }
    }

    public void removeDataList(List<T> dataList) {
        if(dataList != null && !dataList.isEmpty()) {
            this.dataList.removeAll(dataList);
            this.notifyDataSetChanged();
        }

    }

    public ArrayList<T> getArrayDataList() {
        return this.dataList instanceof ArrayList ?(ArrayList)this.dataList:(this.dataList == null?new ArrayList():new ArrayList(this.dataList));
    }

    private int getFixedPosition(int position) {
        return (this.isHeaderEnable()?this.getHeaderItemCount():0) + position;
    }

    private void notifyItemRangeChangedIfNeed(int fromDataPosition) {
        int contentCount = this.getContentItemCount();
        if(fromDataPosition != contentCount - 1) {
            this.notifyItemRangeChanged(this.getFixedPosition(fromDataPosition), contentCount - fromDataPosition);
        }

    }
}
