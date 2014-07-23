package com.antew.rxjava.data.imgur.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImgurGallery {
    @SerializedName("data")
    List<ImgurImage> images;

    public List<ImgurImage> getImages() {
        return images;
    }

    public void setImages(List<ImgurImage> images) {
        this.images = images;
    }
}

