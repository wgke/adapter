package com.wake.recyclerView.adapter.cell.ob;

import android.database.Observable;

import com.wake.recyclerView.adapter.cell.BaseCell;


/**
 * Created by wangke on 2018/11/15.
 */


public class CellObservable extends Observable<CellObserver> {
    public CellObservable() {
    }

    public void notifyCellChange(BaseCell cell) {
        int count = this.mObservers.size();

        for(int i = 0; i < count; ++i) {
            ((CellObserver)this.mObservers.get(i)).onCellChange(cell);
        }

    }

    public boolean hasObserverRegister(CellObserver observer) {
        return observer != null && this.mObservers.contains(observer);
    }

    public void notifyItemChange() {
    }

    public void notifyAdapterDataChange() {
    }
}
