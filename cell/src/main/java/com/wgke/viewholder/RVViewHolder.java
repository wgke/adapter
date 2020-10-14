package com.wgke.viewholder;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/**
 * Created by wangke on 2018/11/15.
 */

public class RVViewHolder extends ViewHolder implements IViewHolder {
    private int layoutId;
    private IViewHolder viewHolderDelegate;
    private ViewDataBinding mViewDataBinding;

    public static RVViewHolder create(Context context, int layoutId, ViewGroup root) {
        View mConvertView = LayoutInflater.from(context).inflate(layoutId, root, false);
        RVViewHolder recyclerViewHolder = new RVViewHolder(mConvertView);
        recyclerViewHolder.setLayoutId(layoutId);
        return recyclerViewHolder;
    }

    public static RVViewHolder create(View itemView) {
        return new RVViewHolder(itemView);
    }


    private RVViewHolder(View itemView) {
        super(itemView);
        this.setViewHolderDelegate(new ViewHolderImpl(itemView));
    }

    public int getLayoutId() {
        return this.layoutId;
    }

    private void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public <T extends View> T getView(int viewId) {
        return this.getViewHolderDelegate().getView(viewId);
    }

    public View getView() {
        return this.getViewHolderDelegate().getView();
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public ViewDataBinding initViewDataBinding(int variableId, @Nullable Object value){
        getViewDataBinding().setVariable(variableId,value);
        getViewDataBinding().executePendingBindings();
        return getViewDataBinding();
    }

    public void setViewDataBinding(ViewDataBinding viewDataBinding) {
        mViewDataBinding = viewDataBinding;
    }

    public IViewHolder setText(int viewId, CharSequence charSequence) {
        return this.getViewHolderDelegate().setText(viewId, charSequence);
    }

    public IViewHolder setImageResource(int viewId, int imageResource) {
        return this.getViewHolderDelegate().setImageResource(viewId, imageResource);
    }

    public IViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        return this.getViewHolderDelegate().setImageBitmap(viewId, bitmap);
    }

    public IViewHolder setImageDrawable(int viewId, Drawable drawable) {
        return this.getViewHolderDelegate().setImageDrawable(viewId, drawable);
    }

    public IViewHolder setBackgroundColor(int viewId, int color) {
        return this.getViewHolderDelegate().setBackgroundColor(viewId, color);
    }

    public IViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        return this.getViewHolderDelegate().setBackgroundRes(viewId, backgroundRes);
    }

    public IViewHolder setTextColor(int viewId, int textColor) {
        return this.getViewHolderDelegate().setTextColor(viewId, textColor);
    }

    public IViewHolder setTextColorRes(int viewId, int textColorRes) {
        return this.getViewHolderDelegate().setTextColorRes(viewId, textColorRes);
    }

    public IViewHolder setAlpha(int viewId, float value) {
        return this.getViewHolderDelegate().setAlpha(viewId, value);
    }

    public IViewHolder setVisible(int viewId, boolean visible) {
        return this.getViewHolderDelegate().setVisible(viewId, visible);
    }

    public IViewHolder setVisibility(int viewId, int visible) {
        return this.getViewHolderDelegate().setVisibility(viewId, visible);
    }

    public IViewHolder addLinks(int viewId, int mask) {
        return this.getViewHolderDelegate().addLinks(viewId, mask);
    }

    public IViewHolder setTypeface(Typeface typeface, int... viewIds) {
        return this.getViewHolderDelegate().setTypeface(typeface, viewIds);
    }

    public IViewHolder setProgress(int viewId, int progress) {
        return this.getViewHolderDelegate().setProgress(viewId, progress);
    }

    public IViewHolder setProgress(int viewId, int progress, int max) {
        return this.getViewHolderDelegate().setProgress(viewId, progress, max);
    }

    public IViewHolder setMax(int viewId, int max) {
        return this.getViewHolderDelegate().setMax(viewId, max);
    }

    public IViewHolder setRating(int viewId, float rating) {
        return this.getViewHolderDelegate().setRating(viewId, rating);
    }

    public IViewHolder setRating(int viewId, float rating, int max) {
        return this.getViewHolderDelegate().setRating(viewId, rating, max);
    }

    public IViewHolder setTag(int viewId, Object tag) {
        return this.getViewHolderDelegate().setTag(viewId, tag);
    }

    public IViewHolder setTag(int viewId, int key, Object tag) {
        return this.getViewHolderDelegate().setTag(viewId, key, tag);
    }

    public IViewHolder setChecked(int viewId, boolean checked) {
        return this.getViewHolderDelegate().setChecked(viewId, checked);
    }

    public IViewHolder setOnClickListener(int viewId, OnClickListener listener) {
        return this.getViewHolderDelegate().setOnClickListener(viewId, listener);
    }

    public IViewHolder setOnTouchListener(int viewId, OnTouchListener listener) {
        return this.getViewHolderDelegate().setOnTouchListener(viewId, listener);
    }

    public IViewHolder setOnLongClickListener(int viewId, OnLongClickListener listener) {
        return this.getViewHolderDelegate().setOnLongClickListener(viewId, listener);
    }

    public IViewHolder setAdapter(int viewId, Adapter adapter) {
        return this.getViewHolderDelegate().setAdapter(viewId, adapter);
    }

    public IViewHolder setOnItemClickListener(int viewId, OnItemClickListener itemClickListener) {
        return this.getViewHolderDelegate().setOnItemClickListener(viewId, itemClickListener);
    }

    public IViewHolder setCompoundDrawablePadding(int viewId, int pad) {
        return this.getViewHolderDelegate().setCompoundDrawablePadding(viewId, pad);
    }

    public IViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        return this.getViewHolderDelegate().setCompoundDrawablesWithIntrinsicBounds(viewId, left, top, right, bottom);
    }

    public IViewHolder setOnCheckedChangeListener(int viewId, OnCheckedChangeListener onCheckedChangeListener) {
        return this.getViewHolderDelegate().setOnCheckedChangeListener(viewId, onCheckedChangeListener);
    }

    public IViewHolder setBackgroundResource(int viewId, int resId) {
        return this.getViewHolderDelegate().setBackgroundResource(viewId, resId);
    }

    public IViewHolder addTextChangedListener(int viewId, TextWatcher textWatcher) {
        return this.getViewHolderDelegate().addTextChangedListener(viewId, textWatcher);
    }

    public IViewHolder setMovementMethod(int viewId, MovementMethod movement) {
        return this.getViewHolderDelegate().setMovementMethod(viewId, movement);
    }

    public IViewHolder setEnable(int viewId, boolean enable) {
        return this.getViewHolderDelegate().setEnable(viewId, enable);
    }

    public int getVisibility(int viewId) {
        return this.getViewHolderDelegate().getVisibility(viewId);
    }

    public IViewHolder setLayoutParams(int viewId, LayoutParams layoutParams) {
        return this.getViewHolderDelegate().setLayoutParams(viewId, layoutParams);
    }

    public IViewHolder setOnItemLongClickListener(int viewId, OnItemLongClickListener onItemLongClickListener) {
        return this.getViewHolderDelegate().setOnItemLongClickListener(viewId, onItemLongClickListener);
    }

    public IViewHolder removeAllViews(int viewId) {
        return this.getViewHolderDelegate().removeAllViews(viewId);
    }

    public IViewHolder addView(int viewId, View childView) {
        return this.getViewHolderDelegate().addView(viewId, childView);
    }

    public IViewHolder setTextSize(int viewId, int unit, float textSize) {
        return this.getViewHolderDelegate().setTextSize(viewId, unit, textSize);
    }

    public IViewHolder setTextSize(int viewId, float textSize) {
        return this.getViewHolderDelegate().setTextSize(viewId, textSize);
    }

    public IViewHolder setText(int viewId, int textRes) {
        return this.getViewHolderDelegate().setText(viewId, textRes);
    }

    public IViewHolder getViewHolderDelegate() {
        return this.viewHolderDelegate;
    }

    public void setViewHolderDelegate(IViewHolder viewHolderDelegate) {
        this.viewHolderDelegate = viewHolderDelegate;
    }
}
