package com.naga.costco.flickr.viewer.java.api;

import com.naga.costco.flickr.viewer.data.ImageSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=61747d6c8bfa112fa68346fd80972b6e")
    Call<ImageSearchResponse> fetchImages(
            @Query("text") String contentType
    );
}
