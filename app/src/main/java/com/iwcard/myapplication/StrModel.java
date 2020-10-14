package com.iwcard.myapplication;

import androidx.databinding.ObservableField;

public class StrModel {
    public ObservableField<String> title;
    public ObservableField<String> content;

    public StrModel(String title, String content) {
        setTitle(title);
        setContent(content);
    }

    public void setTitle(String title) {
        if (this.title == null) {
            this.title = new ObservableField(title);
        } else this.title.set(title);
    }

    public String getTitle() {
        return title == null ? null : title.get();
    }

    public void setContent(String content) {
        if (this.content == null) {
            this.content = new ObservableField(content);
        } else this.content.set(content);
    }

    public String getContent() {
        return content == null ? null : content.get();
    }
}
