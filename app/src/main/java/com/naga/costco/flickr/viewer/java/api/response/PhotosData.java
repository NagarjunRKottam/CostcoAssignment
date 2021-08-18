package com.naga.costco.flickr.viewer.java.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosData {
    @SerializedName("appVersion")
    private int page;

    @SerializedName("appVersion")
    private List<PhotoResponse> photo;
}
