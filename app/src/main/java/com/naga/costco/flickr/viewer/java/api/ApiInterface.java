package com.naga.costco.flickr.viewer.java.api;

import com.naga.costco.flickr.viewer.java.api.response.SearchDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Call<SearchDataResponse> fetchImages(
            @Query("text") String contentType,
            @Query("api_key") String grantType
    );
}
