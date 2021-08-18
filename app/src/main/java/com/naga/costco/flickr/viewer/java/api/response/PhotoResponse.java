package com.naga.costco.flickr.viewer.java.api.response;

import com.google.gson.annotations.SerializedName;

public class PhotoResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("owner")
    private String owner;

    @SerializedName("secret")
    private String secret;

    @SerializedName("server")
    private String server;

    @SerializedName("farm")
    private int farm;

    @SerializedName("title")
    private String title;
}

