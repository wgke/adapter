package com.wgke.adapter.cell;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.wgke.adapter.cell.ob.CellObservable;
import com.wgke.adapter.cell.ob.CellObserver;
import com.wgke.viewholder.RVViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangke on 2018/11/15.
 */

public abstract class BaseCell implements Cell {
    private Map<String, Object> tags;
    private List<OnAttachStateChangeListener> attachStateChangeListeners;
    private int layoutId;
    private int spanSize;
    private CellObservable cellObservable;
    @Nullable
    private RVViewHolder viewHolder;

    public BaseCell(@LayoutRes int layoutId) {
        this(layoutId, 1);
    }

    public BaseCell(@LayoutRes int layoutId, int spanSize) {
        this.cellObservable = new CellObservable();
        this.layoutId = layoutId;
        this.spanSize = spanSize;
    }

    public abstract void onBindData(RVViewHolder var1);

    public int getLayoutId() {
        return this.layoutId;
    }

    public int getSpanSize() {
        return this.spanSize;
    }

    public void onViewAttachedToWindow(RVViewHolder viewHolder) {
        this.viewHolder = viewHolder;
        this.notifyAttachState(viewHolder);
    }

    @Nullable
    public RVViewHolder getAttachedViewHolder() {
        return this.viewHolder;
    }

    public void onViewDetachedFromWindow(RVViewHolder viewHolder) {
        this.viewHolder = null;
        this.notifyDetachState(viewHolder);
    }

    public void registerCellObserver(CellObserver observer) {
        if(!this.cellObservable.hasObserverRegister(observer)) {
            this.cellObservable.registerObserver(observer);
        }

    }

    public void unRegisterCellObserver(CellObserver observer) {
        if(this.cellObservable.hasObserverRegister(observer)) {
            this.cellObservable.unregisterObserver(observer);
        }

    }

    public void onViewCreated(RVViewHolder viewHolder) {
    }

    public void notifyCellChange(BaseCell cell) {
        this.cellObservable.notifyCellChange(cell);
    }

    public void notifyCellChange() {
        this.cellObservable.notifyCellChange(this);
    }

    public void addTag(String key, Object tag) {
        if(this.tags == null) {
            this.tags = new HashMap();
        }

        this.tags.put(key, tag);
    }

    @Nullable
    public <T> T getTag(String key) {
        return this.tags == null?null: (T) this.tags.get(key);
    }

    public void addOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        if(this.attachStateChangeListeners == null) {
            this.attachStateChangeListeners = new ArrayList();
        }

        if(!this.attachStateChangeListeners.contains(listener)) {
            this.attachStateChangeListeners.add(listener);
            if(this.getAttachedViewHolder() != null) {
                listener.onViewAttachedToWindow(this.getAttachedViewHolder());
            }
        }

    }

    public void removeOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        if(this.attachStateChangeListeners != null) {
            this.attachStateChangeListeners.remove(listener);
        }
    }

    private void notifyAttachState(RVViewHolder viewHolder) {
        if(this.attachStateChangeListeners != null) {
            Iterator var2 = this.attachStateChangeListeners.iterator();

            while(var2.hasNext()) {
                OnAttachStateChangeListener listener = (OnAttachStateChangeListener)var2.next();
                listener.onViewAttachedToWindow(viewHolder);
            }
        }

    }

    private void notifyDetachState(RVViewHolder viewHolder) {
        if(this.attachStateChangeListeners != null) {
            Iterator var2 = this.attachStateChangeListeners.iterator();

            while(var2.hasNext()) {
                OnAttachStateChangeListener listener = (OnAttachStateChangeListener)var2.next();
                listener.onViewDetachedFromWindow(viewHolder);
            }
        }

    }
}
