package com.wgke.divider;

/**
 * Created by wangke on 2018/11/15.
 */

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class RVItemDivider extends ItemDecoration {
    private int dividerWidth = 20;
    private Drawable dividerDrawable;
    private boolean skipLast;

    public RVItemDivider(int dividerColor, int dividerWidth) {
        this.dividerDrawable = new ColorDrawable(dividerColor);
        this.dividerWidth = dividerWidth;
    }

    public RVItemDivider(@NonNull Drawable dividerDrawable, int dividerWidth) {
        this.dividerWidth = dividerWidth;
        this.dividerDrawable = dividerDrawable;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        LayoutManager layoutManager = parent.getLayoutManager();
        int orientation;
        if(layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            orientation = gridLayoutManager.getSpanCount();
            int itemCount = gridLayoutManager.getItemCount();
            int rowCount = itemCount % orientation == 0?itemCount / orientation:itemCount / orientation + 1;
            int position = gridLayoutManager.getPosition(view);
            int rowIndex = getRowIndex(position, orientation);
            int columnIndex = position % orientation;
            int l = 0;
            int t = 0;
            int r = 0;
            int b = 0;
            orientation = gridLayoutManager.getOrientation();
            if(orientation == 1) {
                l = getVerticalGridItemLeftOffset(columnIndex, orientation, this.dividerWidth);
                t = getVerticalGridItemTopOffset(rowIndex, rowCount, this.dividerWidth);
            } else if(orientation == 0) {
                l = getHorizontalGridItemLeftOffset(rowIndex, rowCount, this.dividerWidth);
                t = getHorizontalGridItemTopOffset(columnIndex, orientation, this.dividerWidth);
            }

            outRect.set(l, t, r, b);
        } else if(layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;
            if(this.skipLast && linearLayoutManager.getPosition(view) == layoutManager.getItemCount() - 1) {
                return;
            }

            orientation = linearLayoutManager.getOrientation();
            if(orientation == 0) {
                outRect.set(0, 0, this.dividerWidth, 0);
            } else if(orientation == 1) {
                outRect.set(0, 0, 0, this.dividerWidth);
            }
        } else if(layoutManager instanceof StaggeredGridLayoutManager) {
            throw new IllegalArgumentException(" unSupport ");
        }

    }

    public void onDraw(Canvas c, RecyclerView parent, State state) {
        LayoutManager layoutManager = parent.getLayoutManager();
        int spanCount;
        int itemCount;
        int rowCount;
        int vl;
        int vt;
        if(layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            spanCount = gridLayoutManager.getSpanCount();
            itemCount = gridLayoutManager.getItemCount();
            rowCount = itemCount % spanCount == 0?itemCount / spanCount:itemCount / spanCount + 1;
            int childCount = parent.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View childView = parent.getChildAt(i);
                LayoutParams lp = (LayoutParams)childView.getLayoutParams();
                vl = childView.getLeft() - lp.leftMargin;
                vt = childView.getTop() - lp.topMargin;
                int vr = childView.getRight() + lp.rightMargin;
                int vb = childView.getBottom() + lp.bottomMargin;
                int position = gridLayoutManager.getPosition(childView);
                int rowIndex = getRowIndex(position, spanCount);
                int columnIndex = position % spanCount;
                int orientation = gridLayoutManager.getOrientation();
                int l;
                int t;
                boolean isLast;
                int fixSize;
                if(orientation == 1) {
                    l = getVerticalGridItemLeftOffset(columnIndex, spanCount, this.dividerWidth);
                    t = getVerticalGridItemTopOffset(rowIndex, rowCount, this.dividerWidth);
                    if(l > 0) {
                        isLast = this.isVerticalGridItemBottomLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
                        fixSize = isLast?0:l;
                        this.drawDivider(c, vl - l, vt - l, vl, vb + fixSize);
                    }

                    if(t > 0) {
                        isLast = this.isVerticalGridItemRightLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
                        fixSize = isLast?0:l;
                        this.drawDivider(c, vl - t, vt - t, vr + fixSize, vt);
                    }
                } else if(orientation == 0) {
                    l = getHorizontalGridItemLeftOffset(rowIndex, rowCount, this.dividerWidth);
                    t = getHorizontalGridItemTopOffset(columnIndex, spanCount, this.dividerWidth);
                    if(l > 0) {
                        isLast = this.isHorizontalGridBottomLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
                        fixSize = isLast?0:l;
                        this.drawDivider(c, vl - l, vt - l, vl, vb + fixSize);
                    }

                    if(t > 0) {
                        isLast = this.isHorizontalGridRightLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
                        fixSize = isLast?0:l;
                        this.drawDivider(c, vl - t, vt - t, vr + fixSize, vt);
                    }
                }
            }
        } else if(layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;
            spanCount = linearLayoutManager.getOrientation();
            itemCount = parent.getChildCount();

            for(rowCount = 0; rowCount < itemCount; ++rowCount) {
                if(this.skipLast && rowCount == itemCount - 1) {
                    return;
                }

                View childView = parent.getChildAt(rowCount);
                LayoutParams lp = (LayoutParams)childView.getLayoutParams();
                int l;
                int t;
                if(spanCount == 0) {
                    l = childView.getRight() + lp.rightMargin;
                    t = childView.getTop() - lp.topMargin;
                    vl = l + this.dividerWidth;
                    vt = childView.getBottom() + lp.bottomMargin;
                    this.drawDivider(c, l, t, vl, vt);
                } else if(spanCount == 1) {
                    l = childView.getLeft() - lp.leftMargin;
                    t = childView.getBottom() + lp.bottomMargin;
                    vl = childView.getRight() + lp.rightMargin;
                    vt = t + this.dividerWidth;
                    this.drawDivider(c, l, t, vl, vt);
                }
            }
        } else if(layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager var26 = (StaggeredGridLayoutManager)layoutManager;
        }

    }

    private void drawDivider(Canvas canvas, int l, int t, int r, int b) {
        if(this.dividerDrawable != null) {
            this.dividerDrawable.setBounds(l, t, r, b);
            this.dividerDrawable.draw(canvas);
        }
    }

    private static int getVerticalGridItemLeftOffset(int columnIndex, int spanCount, int dividerWidth) {
        int l = 0;
        if(spanCount != 1 && columnIndex != 0) {
            l = dividerWidth;
        }

        return l;
    }

    private static int getVerticalGridItemTopOffset(int rowIndex, int rowCount, int dividerWidth) {
        int t = 0;
        if(rowCount != 1 && rowIndex != 0) {
            t = dividerWidth;
        }

        return t;
    }

    private static int getHorizontalGridItemLeftOffset(int rowIndex, int rowCount, int dividerWidth) {
        return getVerticalGridItemTopOffset(rowIndex, rowCount, dividerWidth);
    }

    private static int getHorizontalGridItemTopOffset(int columnIndex, int spanCount, int dividerWidth) {
        return getVerticalGridItemLeftOffset(columnIndex, spanCount, dividerWidth);
    }

    private static int getRowIndex(int position, int spanCount) {
        ++position;
        int rowIndex = position % spanCount == 0?position / spanCount:position / spanCount + 1;
        return rowIndex - 1;
    }

    private boolean isVerticalGridItemBottomLast(int itemCount, int rowIndex, int rowCount, int columnIndex, int spanCount) {
        return rowIndex == rowCount - 1 || rowIndex == rowCount - 2 && itemCount % spanCount != 0 && columnIndex > itemCount % spanCount - 1;
    }

    private boolean isVerticalGridItemRightLast(int itemCount, int rowIndex, int rowCount, int columnIndex, int spanCount) {
        return rowIndex == rowCount - 1 && columnIndex == itemCount % spanCount - 1;
    }

    private boolean isHorizontalGridRightLast(int itemCount, int rowIndex, int rowCount, int columnIndex, int spanCount) {
        return this.isVerticalGridItemBottomLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
    }

    private boolean isHorizontalGridBottomLast(int itemCount, int rowIndex, int rowCount, int columnIndex, int spanCount) {
        return this.isVerticalGridItemRightLast(itemCount, rowIndex, rowCount, columnIndex, spanCount);
    }

    public boolean isSkipLast() {
        return this.skipLast;
    }

    public void setSkipLast(boolean skipLast) {
        this.skipLast = skipLast;
    }
}
