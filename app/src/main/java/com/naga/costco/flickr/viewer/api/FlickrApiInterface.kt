package com.naga.costco.flickr.viewer.api

import com.naga.costco.flickr.viewer.data.ImageSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiInterface {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun fetchImages(
        @Query(value = "text") searchTerm: String,
        @Query("api_key") apiKey: String = API_KEY
    ): ImageSearchResponse

    companion object {
        const val API_KEY = "61747d6c8bfa112fa68346fd80972b6e"
    }
}
