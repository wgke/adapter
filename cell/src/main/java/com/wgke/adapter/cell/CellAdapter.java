package com.wgke.adapter.cell;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.wgke.adapter.BaseObjectRecyclerAdapter;
import com.wgke.adapter.cell.ob.CellObserver;
import com.wgke.cell.R;
import com.wgke.viewholder.RVViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by wangke on 2018/11/15.
 */


public final class CellAdapter extends BaseObjectRecyclerAdapter<Cell, RVViewHolder> {
    private SparseIntArray viewTypeIds = new SparseIntArray();
    private List<Cell> headerCells = new ArrayList();
    private List<Cell> footerCells = new ArrayList();
    private CellObserver cellObserver = new CellObserver() {
        public void onCellChange(Cell cell) {
            int position = CellAdapter.this.findPositionOfCell(cell);
            if(position != -1) {
                CellAdapter.this.notifyItemChanged(position);
            }

        }

        public void onCellInsert(Cell cell) {
        }

        public void onCellRemove(Cell cell) {
        }
    };
    private View.OnAttachStateChangeListener attachStateChangeListener = new View.OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View v) {
        }

        public void onViewDetachedFromWindow(View v) {
            RecyclerView recyclerView = (RecyclerView)v;
            int childCount = recyclerView.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View view = recyclerView.getChildAt(i);
                RVViewHolder viewHolder = (RVViewHolder)recyclerView.getChildViewHolder(view);
                CellAdapter.this.notifyViewDetachedFromWindow(viewHolder);
            }

        }
    };

    public CellAdapter(Context context) {
        super(context);
    }

    public CellAdapter(Context context, List<Cell> dataList) {
        super(context, dataList);
    }

    public RVViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        int layoutId = this.viewTypeIds.get(viewType);
        RVViewHolder viewHolder = RVViewHolder.create(this.context, this.viewTypeIds.get(viewType), parent);
        int contentSize = this.getDataList().size();

        for(int i = 0; i < contentSize; ++i) {
            Cell cell = (Cell)this.getDataAt(i);
            if(cell.getLayoutId() == layoutId) {
                cell.onViewCreated(viewHolder);
                break;
            }
        }

        return viewHolder;
    }

    public void onBindContentViewHolder(RVViewHolder holder, int position) {
        Cell cell = (Cell)this.getDataAt(position);
        cell.onBindData(holder);
    }

    public RVViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        Cell headerCell = (Cell)this.headerCells.get(0);
        int layoutId = headerCell.getLayoutId();
        RVViewHolder viewHolder = RVViewHolder.create(this.context, layoutId, parent);
        headerCell.onViewCreated(viewHolder);
        return viewHolder;
    }

    public void onBindHeaderViewHolder(RVViewHolder holder, int position) {
        Cell cell = (Cell)this.headerCells.get(position);
        cell.onBindData(holder);
    }

    public RVViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        Cell footerCell = (Cell)this.footerCells.get(0);
        int layoutId = footerCell.getLayoutId();
        RVViewHolder viewHolder = RVViewHolder.create(this.context, layoutId, parent);
        footerCell.onViewCreated(viewHolder);
        return viewHolder;
    }

    public void onBindFooterViewHolder(RVViewHolder holder, int position) {
        Cell cell = (Cell)this.footerCells.get(position);
        cell.onBindData(holder);
    }

    public void addHeaderCell(Cell cell) {
        int headerCount = this.headerCells.size();
        this.headerCells.add(cell);
        if(this.isHeaderEnable()) {
            this.notifyItemRangeInserted(headerCount, 1);
        }

    }

    public void removeHeaderCell(Cell cell) {
        if(cell != null) {
            this.removeHeaderCellAt(this.headerCells.indexOf(cell));
        }
    }

    public void clearHeaderCell() {
        int headerCount = this.headerCells.size();
        this.headerCells.clear();
        if(this.isHeaderEnable()) {
            this.notifyItemRangeRemoved(0, headerCount);
        }

    }

    public Cell getHeaderCellAt(int index) {
        return (Cell)this.headerCells.get(index);
    }

    public void removeHeaderCellAt(int position) {
        int headerCount = this.headerCells.size();
        if(position >= 0 && position <= headerCount - 1) {
            this.headerCells.remove(position);
            if(this.isHeaderEnable()) {
                this.notifyItemRangeRemoved(position, 1);
                if(position != headerCount - 1) {
                    this.notifyItemRangeChanged(position, headerCount - 1 - position);
                }
            }

        }
    }

    public void addFooterCell(Cell cell) {
        this.footerCells.add(cell);
        if(this.isFooterEnable(true)) {
            this.notifyItemRangeInserted(this.getItemCount() - 1, 1);
        }

    }

    public void clearFooterCell() {
        int footerCount = this.footerCells.size();
        this.footerCells.clear();
        if(this.isFooterEnable(true)) {
            int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
            this.notifyItemRangeRemoved(this.getContentItemCount() + validHeaderCount, footerCount);
        }

    }

    public void removeFooterCellAt(int position) {
        int footerCount = this.footerCells.size();
        if(position >= 0 && position <= footerCount - 1) {
            this.footerCells.remove(position);
            if(this.isFooterEnable(true)) {
                int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                int startPosition = position + this.getContentItemCount() + validHeaderCount;
                this.notifyItemRemoved(startPosition);
                if(position != footerCount - 1) {
                    this.notifyItemRangeChanged(startPosition, footerCount - 1 - position);
                }
            }

        }
    }

    public void removeFooterCell(Cell cell) {
        if(cell != null) {
            this.removeFooterCellAt(this.footerCells.indexOf(cell));
        }
    }

    public Cell getFooterCellAt(int index) {
        return (Cell)this.footerCells.get(index);
    }

    public int getHeaderItemCount() {
        return this.headerCells.size();
    }

    public int getFooterItemCount() {
        return this.footerCells.size();
    }

    public int getContentItemViewType(int position) {
        Cell cell = (Cell)this.getDataAt(position);
        int layoutId = cell.getLayoutId();
        int viewType = this.viewTypeIds.indexOfValue(layoutId);
        if(viewType == -1) {
            viewType = this.viewTypeIds.size();
            this.viewTypeIds.put(viewType, layoutId);
        }

        return viewType;
    }

    public boolean onFailedToRecycleView(RVViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.removeOnAttachStateChangeListener(this.attachStateChangeListener);
        recyclerView.addOnAttachStateChangeListener(this.attachStateChangeListener);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int position) {
                    int fixedPosition = CellAdapter.this.fixLoopPosition(position);
                    if(CellAdapter.this.isContentItem(fixedPosition)) {
                        int validHeaderCount = CellAdapter.this.isHeaderEnable()?CellAdapter.this.getHeaderItemCount():0;
                        Cell cell = (Cell)CellAdapter.this.getDataAt(fixedPosition - validHeaderCount);
                        int spanSize = cell.getSpanSize();
                        if(spanSize == -1) {
                            spanSize = ((GridLayoutManager)layoutManager).getSpanCount();
                        }

                        return spanSize;
                    } else {
                        return oldSizeLookup != null?oldSizeLookup.getSpanSize(position):1;
                    }
                }
            });
        }

    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.removeOnAttachStateChangeListener(this.attachStateChangeListener);
        int childCount = recyclerView.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            View view = recyclerView.getChildAt(i);
            RVViewHolder viewHolder = (RVViewHolder)recyclerView.getChildViewHolder(view);
            this.notifyViewDetachedFromWindow(viewHolder);
        }

    }

    public void onViewAttachedToWindow(RVViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        this.notifyViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        position = this.fixLoopPosition(position);
        if(position != -1) {
            if(this.isContentItem(position)) {
                int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                Cell cell = (Cell)this.getDataAt(position - validHeaderCount);
                if(cell.getSpanSize() == -1) {
                    ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                    if(lp != null && lp instanceof  StaggeredGridLayoutManager.LayoutParams) {
                         StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams)lp;
                        p.setFullSpan(true);
                    }
                }
            }

        }
    }

    public void onViewDetachedFromWindow(RVViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        this.notifyViewDetachedFromWindow(holder);
    }

    public int getViewTypeOfCell(Cell cell) {
        int position = this.viewTypeIds.indexOfValue(cell.getLayoutId());
        return position != -1?this.viewTypeIds.keyAt(position):-1;
    }

    public int findPositionOfCell(Cell cell) {
        int cellPosition = this.findCellPositionFromHeader(cell);
        if(cellPosition == -1) {
            cellPosition = this.findCellPositionFromContent(cell);
            if(cellPosition != -1) {
                cellPosition += this.isHeaderEnable()?this.getHeaderItemCount():0;
                return cellPosition;
            }

            cellPosition = this.findCellPositionFromFooter(cell);
            if(cellPosition != -1) {
                cellPosition += this.getContentItemCount() + (this.isHeaderEnable()?this.getHeaderItemCount():0);
            }
        }

        return cellPosition;
    }

    public int findFirstPositionOfView(@LayoutRes int layoutId) {
        int headerCount = this.getHeaderItemCount();

        int contentCount;
        for(contentCount = 0; contentCount < headerCount; ++contentCount) {
            Cell cell = (Cell)this.headerCells.get(contentCount);
            if(cell.getLayoutId() == layoutId) {
                return contentCount;
            }
        }

        contentCount = this.getContentItemCount();

        int footerCount;
        for(footerCount = 0; footerCount < contentCount; ++footerCount) {
            Cell cell = (Cell)this.getDataAt(footerCount);
            if(cell.getLayoutId() == layoutId) {
                return headerCount + footerCount;
            }
        }

        footerCount = this.getFooterItemCount();

        for(int i = 0; i < footerCount; ++i) {
            Cell cell = (Cell)this.footerCells.get(i);
            if(cell.getLayoutId() == layoutId) {
                return headerCount + contentCount + i;
            }
        }

        return -1;
    }

    public <T extends Cell> T findFirstCellOfView(@LayoutRes int layoutId) {
        int headerCount = this.getHeaderItemCount();

        int contentCount;
        for(contentCount = 0; contentCount < headerCount; ++contentCount) {
            Cell cell = (Cell)this.headerCells.get(contentCount);
            if(cell.getLayoutId() == layoutId) {
                return (T) cell;
            }
        }

        contentCount = this.getContentItemCount();

        int footerCount;
        for(footerCount = 0; footerCount < contentCount; ++footerCount) {
            Cell cell = (Cell)this.getDataAt(footerCount);
            if(cell.getLayoutId() == layoutId) {
                return (T) cell;
            }
        }

        footerCount = this.getFooterItemCount();

        for(int i = 0; i < footerCount; ++i) {
            Cell cell = (Cell)this.footerCells.get(i);
            if(cell.getLayoutId() == layoutId) {
                return (T) cell;
            }
        }

        return null;
    }

    private int findCellPositionFromHeader(Cell cell) {
        return this.isHeaderEnable() && cell != null?this.headerCells.indexOf(cell):-1;
    }

    private int findCellPositionFromContent(Cell cell) {
        return this.getDataIndex(cell);
    }

    private int findCellPositionFromFooter(Cell cell) {
        return this.isFooterEnable(true) && cell != null?this.footerCells.indexOf(cell):-1;
    }

    private void notifyViewAttachedToWindow(RVViewHolder holder) {
        int position = holder.getAdapterPosition();
        position = this.fixLoopPosition(position);
        Cell cell = this.findCellAtAdapterPosition(position);
        if(cell != null) {
            RVViewHolder oldViewHolder = cell.getAttachedViewHolder();
            if(oldViewHolder != null && oldViewHolder != holder) {
                cell.onViewDetachedFromWindow(oldViewHolder);
            }

            cell.onViewAttachedToWindow(holder);
            cell.registerCellObserver(this.cellObserver);
            holder.getView().setTag(R.integer.tag_key_cell, cell);
        }

    }

    private void notifyViewDetachedFromWindow(RVViewHolder holder) {
        Cell cell = (Cell)holder.getView().getTag(R.integer.tag_key_cell);
        holder.getView().setTag(R.integer.tag_key_cell, (Object)null);
        if(cell != null) {
            RVViewHolder oldViewHolder = cell.getAttachedViewHolder();
            if(holder.equals(oldViewHolder)) {
                cell.onViewDetachedFromWindow(holder);
                cell.unRegisterCellObserver(this.cellObserver);
            }
        }

    }

    public Cell findCellAtAdapterPosition(int position) {
        if(position == -1) {
            return null;
        } else if(this.isHeaderItem(position)) {
            return (Cell)this.headerCells.get(position);
        } else {
            int validHeaderCount;
            if(this.isContentItem(position)) {
                validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                return (Cell)this.getDataAt(position - validHeaderCount);
            } else if(this.isFooterItem(position)) {
                validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                return (Cell)this.footerCells.get(position - validHeaderCount - this.getContentItemCount());
            } else {
                return null;
            }
        }
    }

    public <T extends Cell> T findCell(Class<T> cellClass) {
        List<Cell> cellList = this.getDataList();
        Iterator var3 = cellList.iterator();

        Cell cell;
        do {
            if(!var3.hasNext()) {
                return null;
            }

            cell = (Cell)var3.next();
        } while(!cellClass.isAssignableFrom(cell.getClass()));

        return (T) cell;
    }
}
