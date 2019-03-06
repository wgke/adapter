package com.wgke.viewholder;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

/**
 * Created by wangke on 2018/11/15.
 */

public interface IViewHolder {
    <T extends View> T getView(@IdRes int var1);

    View getView();

    IViewHolder setText(@IdRes int var1, CharSequence var2);

    IViewHolder setImageResource(@IdRes int var1, int var2);

    IViewHolder setImageBitmap(@IdRes int var1, Bitmap var2);

    IViewHolder setImageDrawable(@IdRes int var1, Drawable var2);

    IViewHolder setBackgroundColor(@IdRes int var1, int var2);

    IViewHolder setBackgroundRes(@IdRes int var1, int var2);

    IViewHolder setTextColor(@IdRes int var1, int var2);

    IViewHolder setTextColorRes(@IdRes int var1, int var2);

    IViewHolder setAlpha(@IdRes int var1, float var2);

    IViewHolder setVisible(@IdRes int var1, boolean var2);

    IViewHolder setVisibility(@IdRes int var1, int var2);

    IViewHolder addLinks(@IdRes int var1, int var2);

    IViewHolder setTypeface(Typeface var1, int... var2);

    IViewHolder setProgress(@IdRes int var1, int var2);

    IViewHolder setProgress(@IdRes int var1, int var2, int var3);

    IViewHolder setMax(@IdRes int var1, int var2);

    IViewHolder setRating(@IdRes int var1, float var2);

    IViewHolder setRating(@IdRes int var1, float var2, int var3);

    IViewHolder setTag(@IdRes int var1, Object var2);

    IViewHolder setTag(@IdRes int var1, int var2, Object var3);

    IViewHolder setChecked(@IdRes int var1, boolean var2);

    IViewHolder setOnClickListener(@IdRes int var1, OnClickListener var2);

    IViewHolder setOnTouchListener(@IdRes int var1, OnTouchListener var2);

    IViewHolder setOnLongClickListener(@IdRes int var1, OnLongClickListener var2);

    IViewHolder setAdapter(@IdRes int var1, Adapter var2);

    IViewHolder setOnItemClickListener(@IdRes int var1, OnItemClickListener var2);

    IViewHolder setCompoundDrawablePadding(@IdRes int var1, int var2);

    IViewHolder setCompoundDrawablesWithIntrinsicBounds(@IdRes int var1, Drawable var2, Drawable var3, Drawable var4, Drawable var5);

    IViewHolder setOnCheckedChangeListener(@IdRes int var1, OnCheckedChangeListener var2);

    IViewHolder setBackgroundResource(@IdRes int var1, int var2);

    IViewHolder addTextChangedListener(@IdRes int var1, TextWatcher var2);

    IViewHolder setMovementMethod(@IdRes int var1, MovementMethod var2);

    IViewHolder setEnable(@IdRes int var1, boolean var2);

    int getVisibility(@IdRes int var1);

    IViewHolder setLayoutParams(@IdRes int var1, LayoutParams var2);

    IViewHolder setOnItemLongClickListener(@IdRes int var1, OnItemLongClickListener var2);

    IViewHolder removeAllViews(@IdRes int var1);

    IViewHolder addView(@IdRes int var1, View var2);

    IViewHolder setTextSize(@IdRes int var1, int var2, float var3);

    IViewHolder setTextSize(@IdRes int var1, float var2);

    IViewHolder setText(@IdRes int var1, @StringRes int var2);
}
