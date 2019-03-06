package com.wgke.viewholder;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * Created by wangke on 2018/11/15.
 */

public class ViewHolderImpl implements IViewHolder {
    private SparseArray<View> views = new SparseArray();
    private View contentView;
    private Context context;

    public ViewHolderImpl(View contentView) {
        this.contentView = contentView;
        this.context = contentView.getContext();
    }

    public <T extends View> T getView(int viewId) {
        View view = (View) this.views.get(viewId);
        if (view == null) {
            view = this.getView().findViewById(viewId);
            if (view != null) {
                this.views.put(viewId, view);
            }
        }
        return (T) view;
    }

    public View getView() {
        return this.contentView;
    }

    public IViewHolder setText(int viewId, CharSequence charSequence) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setText(charSequence);
        return this;
    }

    public IViewHolder setImageResource(int viewId, int imageResource) {
        ImageView imageView = (ImageView) this.getView(viewId);
        imageView.setImageResource(imageResource);
        return this;
    }

    public IViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = (ImageView) this.getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public IViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = (ImageView) this.getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public IViewHolder setBackgroundColor(int viewId, int color) {
        View view = this.getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public IViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = this.getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public IViewHolder setTextColor(int viewId, int textColor) {
        TextView view = (TextView) this.getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public IViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = (TextView) this.getView(viewId);
        view.setTextColor(this.context.getResources().getColor(textColorRes));
        return this;
    }

    public IViewHolder setAlpha(int viewId, float value) {
        this.getView(viewId).setAlpha(value);
        return this;
    }

    public IViewHolder setVisible(int viewId, boolean visible) {
        View view = this.getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public IViewHolder setVisibility(int viewId, int visible) {
        View view = this.getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public IViewHolder addLinks(int viewId, int mask) {
        TextView view = (TextView) this.getView(viewId);
        Linkify.addLinks(view, mask);
        return this;
    }

    public IViewHolder setTypeface(Typeface typeface, int... viewIds) {
        int[] var3 = viewIds;
        int var4 = viewIds.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int viewId = var3[var5];
            TextView view = (TextView) this.getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | 128);
        }

        return this;
    }

    public IViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = (ProgressBar) this.getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public IViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = (ProgressBar) this.getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public IViewHolder setMax(int viewId, int max) {
        ProgressBar view = (ProgressBar) this.getView(viewId);
        view.setMax(max);
        return this;
    }

    public IViewHolder setRating(int viewId, float rating) {
        RatingBar view = (RatingBar) this.getView(viewId);
        view.setRating(rating);
        return this;
    }

    public IViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = (RatingBar) this.getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public IViewHolder setTag(int viewId, Object tag) {
        View view = this.getView(viewId);
        view.setTag(tag);
        return this;
    }

    public IViewHolder setTag(int viewId, int key, Object tag) {
        View view = this.getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public IViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) this.getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public IViewHolder setOnClickListener(int viewId, OnClickListener listener) {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public IViewHolder setOnTouchListener(int viewId, OnTouchListener listener) {
        View view = this.getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public IViewHolder setOnLongClickListener(int viewId, OnLongClickListener listener) {
        View view = this.getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public IViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView<Adapter> adapterView = (AdapterView) this.getView(viewId);
        adapterView.setAdapter(adapter);
        return this;
    }

    public IViewHolder setOnItemClickListener(int viewId, OnItemClickListener itemClickListener) {
        AdapterView<Adapter> adapterView = (AdapterView) this.getView(viewId);
        adapterView.setOnItemClickListener(itemClickListener);
        return this;
    }

    public IViewHolder setCompoundDrawablePadding(int viewId, int pad) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setCompoundDrawablePadding(pad);
        return this;
    }

    public IViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return this;
    }

    public IViewHolder setOnCheckedChangeListener(int viewId, OnCheckedChangeListener onCheckedChangeListener) {
        CompoundButton checkBox = (CompoundButton) this.getView(viewId);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    public IViewHolder setBackgroundResource(int viewId, int resId) {
        View v = this.getView(viewId);
        v.setBackgroundResource(resId);
        return this;
    }

    public IViewHolder addTextChangedListener(int viewId, TextWatcher textWatcher) {
        EditText v = (EditText) this.getView(viewId);
        v.addTextChangedListener(textWatcher);
        return this;
    }

    public IViewHolder setMovementMethod(int viewId, MovementMethod movement) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setMovementMethod(movement);
        return this;
    }

    public IViewHolder setEnable(int viewId, boolean enable) {
        this.getView(viewId).setEnabled(enable);
        return this;
    }

    public int getVisibility(int viewId) {
        return this.getView(viewId).getVisibility();
    }

    public IViewHolder setLayoutParams(int viewId, LayoutParams layoutParams) {
        this.getView(viewId).setLayoutParams(layoutParams);
        return this;
    }

    public IViewHolder setOnItemLongClickListener(int viewId, OnItemLongClickListener onItemLongClickListener) {
        AdapterView adapterView = (AdapterView) this.getView(viewId);
        adapterView.setOnItemLongClickListener(onItemLongClickListener);
        return this;
    }

    public IViewHolder removeAllViews(int viewId) {
        ViewGroup viewGroup = (ViewGroup) this.getView(viewId);
        viewGroup.removeAllViews();
        return this;
    }

    public IViewHolder addView(int viewId, View childView) {
        ViewGroup viewGroup = (ViewGroup) this.getView(viewId);
        viewGroup.addView(childView);
        return this;
    }

    public IViewHolder setTextSize(int viewId, int unit, float textSize) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setTextSize(unit, textSize);
        return this;
    }

    public IViewHolder setTextSize(int viewId, float textSize) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setTextSize(2, textSize);
        return this;
    }

    public IViewHolder setText(int viewId, @StringRes int text) {
        TextView textView = (TextView) this.getView(viewId);
        textView.setText(text);
        return this;
    }
}
