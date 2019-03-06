package com.wgke.adapter.cell.ob;


import com.wgke.adapter.cell.Cell;

/**
 * Created by wangke on 2018/11/15.
 */

public interface CellObserver {
    void onCellChange(Cell var1);

    void onCellInsert(Cell var1);

    void onCellRemove(Cell var1);
}
