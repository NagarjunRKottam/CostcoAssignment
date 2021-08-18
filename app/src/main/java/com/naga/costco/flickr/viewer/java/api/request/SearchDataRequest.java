package com.naga.costco.flickr.viewer.java.api.request;

import com.google.gson.annotations.SerializedName;

public class SearchDataRequest {
    @SerializedName("text")
    public String text;

    @SerializedName("api_key")
    public String apikey;
}
