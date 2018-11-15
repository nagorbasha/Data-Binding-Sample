package com.corpus.dev.databinding;


import android.databinding.BaseObservable;

import java.io.Serializable;

public class Channels extends BaseObservable implements Serializable {

    private String name;

    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.obj);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.obj);
    }
}
