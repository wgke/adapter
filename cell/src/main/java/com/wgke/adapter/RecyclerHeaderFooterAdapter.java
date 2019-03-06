package com.wgke.adapter;

/**
 * Created by wangke on 2018/11/15.
 */

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class RecyclerHeaderFooterAdapter<VH extends ViewHolder> extends Adapter<VH> {
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;
    private boolean headerEnable;
    private boolean footerEnable;
    private boolean singleLineHeaderEnable;
    private boolean singleLineFooterEnable;
    private boolean loopEnable;
    private OnItemClickListener onItemClickListener;
    private OnItemLongLickListener onItemLongLickListener;
    @Nullable
    private RecyclerView attachedRecyclerView;
    private int lastRawItemCount;

    public RecyclerHeaderFooterAdapter() {
    }

    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        if(viewType == 1) {
            viewHolder = this.onCreateHeaderViewHolder(parent, viewType);
        } else if(viewType == 2) {
            viewHolder = this.onCreateFooterViewHolder(parent, viewType);
        } else {
            viewHolder = this.onCreateContentViewHolder(parent, viewType - 1 - 2);
        }

        this.onViewHolderCreated((VH) viewHolder);
        return (VH) viewHolder;
    }

    protected void onViewHolderCreated(VH viewHolder) {
    }

    public final void onBindViewHolder(final VH holder, int position) {
        position = this.fixLoopPosition(position);
        int viewType = holder.getItemViewType();
        if(viewType == 1) {
            this.onBindHeaderViewHolder(holder, position);
        } else {
            final int headerCount;
            if(viewType == 2) {
                headerCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                int contentCount = this.getContentItemCount();
                this.onBindFooterViewHolder(holder, position - headerCount - contentCount);
            } else {
                headerCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
                if(this.onItemClickListener != null) {
                    holder.itemView.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            int adapterPosition = holder.getAdapterPosition();
                            if(adapterPosition != -1) {
                                RecyclerHeaderFooterAdapter.this.onItemClickListener.onItemClick(holder, adapterPosition - headerCount);
                            }
                        }
                    });
                }

                if(this.onItemLongLickListener != null) {
                    holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View v) {
                            int adapterPosition = holder.getAdapterPosition();
                            return adapterPosition == -1?false:RecyclerHeaderFooterAdapter.this.onItemLongLickListener.onItemLongClick(holder, adapterPosition - headerCount);
                        }
                    });
                }

                this.onBindContentViewHolder(holder, position - headerCount);
            }
        }

    }

    public final int getItemCount() {
        int itemCount = this.getRawItemCount();
        if(this.loopEnable && itemCount > 0) {
            itemCount = 2147483647;
            if(this.lastRawItemCount != itemCount) {
                this.fixFirstLoopInvalid();
                this.lastRawItemCount = itemCount;
            }
        }

        return itemCount;
    }

    public final int getItemViewType(int position) {
        position = this.fixLoopPosition(position);
        int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
        if(position < validHeaderCount) {
            return 1;
        } else if(position < validHeaderCount + this.getContentItemCount()) {
            int contentItemViewType = this.getContentItemViewType(position - validHeaderCount);
            if(contentItemViewType < 0) {
                throw new IllegalArgumentException("getContentItemViewType must return a value >= 0");
            } else {
                return contentItemViewType + 1 + 2;
            }
        } else {
            return 2;
        }
    }

    public VH onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public VH onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public abstract VH onCreateContentViewHolder(ViewGroup var1, int var2);

    public abstract void onBindContentViewHolder(VH var1, int var2);

    public void onBindFooterViewHolder(VH holder, int position) {
    }

    public void onBindHeaderViewHolder(VH holder, int position) {
    }

    public int getHeaderItemCount() {
        return 0;
    }

    public int getFooterItemCount() {
        return 0;
    }

    public abstract int getContentItemCount();

    public int getContentItemViewType(int position) {
        return 0;
    }

    public final boolean isHeaderEnable() {
        return this.headerEnable;
    }

    public void setHeaderEnable(boolean headerEnable) {
        int headerCount;
        if(!this.isHeaderEnable() && headerEnable) {
            headerCount = this.getHeaderItemCount();
            this.notifyItemRangeInserted(0, headerCount);
            this.notifyItemRangeChanged(headerCount, this.getContentItemCount());
        }

        if(this.isHeaderEnable() && !headerEnable) {
            headerCount = this.getHeaderItemCount();
            this.notifyItemRangeRemoved(0, headerCount);
            this.notifyItemRangeChanged(0, this.getContentItemCount());
        }

        this.headerEnable = headerEnable;
    }

    public final boolean isFooterEnable(boolean b) {
        return this.footerEnable;
    }

    public void setFooterEnable(boolean footerEnable) {
        int validHeaderCount;
        int startPosition;
        if(!this.isFooterEnable(true) && footerEnable) {
            validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
            startPosition = validHeaderCount + this.getContentItemCount();
            this.notifyItemRangeInserted(startPosition, this.getFooterItemCount());
        }

        if(this.isFooterEnable(true) && !footerEnable) {
            validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
            startPosition = validHeaderCount + this.getContentItemCount();
            this.notifyItemRangeRemoved(startPosition, this.getFooterItemCount());
        }

        this.footerEnable = footerEnable;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongLickListener(OnItemLongLickListener onItemLongLickListener) {
        this.onItemLongLickListener = onItemLongLickListener;
    }

    public boolean isHeaderItem(int position) {
        return this.isHeaderEnable() && position < this.getHeaderItemCount();
    }

    public boolean isContentItem(int position) {
        int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
        return position >= validHeaderCount && position < this.getContentItemCount() + validHeaderCount;
    }

    public boolean isFooterItem(int position) {
        int validHeaderCount = this.isHeaderEnable()?this.getHeaderItemCount():0;
        return position >= this.getContentItemCount() + validHeaderCount;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.attachedRecyclerView = recyclerView;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int position) {
                    int fixedPosition = RecyclerHeaderFooterAdapter.this.fixLoopPosition(position);
                    return RecyclerHeaderFooterAdapter.this.isHeaderItem(fixedPosition) && RecyclerHeaderFooterAdapter.this.singleLineHeaderEnable?gridLayoutManager.getSpanCount():(RecyclerHeaderFooterAdapter.this.isFooterItem(fixedPosition) && RecyclerHeaderFooterAdapter.this.singleLineFooterEnable?gridLayoutManager.getSpanCount():(oldSizeLookup != null?oldSizeLookup.getSpanSize(position):0));
                }
            });
        }

    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.attachedRecyclerView = null;
    }

    @Nullable
    public RecyclerView getAttachedRecyclerView() {
        return this.attachedRecyclerView;
    }

    private void fixFirstLoopInvalid() {
        RecyclerView recyclerView = this.getAttachedRecyclerView();
        if(recyclerView != null) {
            int itemCount = this.getRawItemCount();
            if(itemCount > 0) {
                recyclerView.scrollToPosition(2147483647 / itemCount / 2 * itemCount);
            }
        }
    }

    public int fixLoopPosition(int position) {
        return this.loopEnable && position > 0?position % this.getRawItemCount():position;
    }

    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        position = this.fixLoopPosition(position);
        if(this.isHeaderItem(position) && this.singleLineHeaderEnable || this.isFooterItem(position) && this.singleLineFooterEnable) {
            LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams)lp;
                p.setFullSpan(true);
            }
        }

    }

    public boolean isSingleLineHeaderEnable() {
        return this.singleLineHeaderEnable;
    }

    public void setSingleLineHeaderEnable(boolean singleLineHeaderEnable) {
        this.singleLineHeaderEnable = singleLineHeaderEnable;
    }

    public boolean isSingleLineFooterEnable() {
        return this.singleLineFooterEnable;
    }

    public void setSingleLineFooterEnable(boolean singleLineFooterEnable) {
        this.singleLineFooterEnable = singleLineFooterEnable;
    }

    public int getRawItemCount() {
        int itemCount = 0;
        if(this.isHeaderEnable()) {
            itemCount += this.getHeaderItemCount();
        }

        if(this.isFooterEnable(true)) {
            itemCount += this.getFooterItemCount();
        }

        itemCount += this.getContentItemCount();
        return itemCount;
    }

    public boolean isLoopEnable() {
        return this.loopEnable;
    }

    public void setLoopEnable(boolean loopEnable) {
        if(this.loopEnable != loopEnable) {
            this.loopEnable = loopEnable;
            this.notifyDataSetChanged();
        }
    }
}
