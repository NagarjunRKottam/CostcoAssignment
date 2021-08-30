package com.naga.costco.flickr.viewer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naga.costco.flickr.viewer.api.RetrofitInstance
import com.naga.costco.flickr.viewer.data.ImageSearchResponse
import com.naga.costco.flickr.viewer.data.Photo
import com.naga.costco.flickr.viewer.java.api.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : ViewModel() {

    private val mutableImagesLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mutableImagesLiveData

    fun fetchImages(searchTerm: String) {
        if (searchTerm.isBlank()) {
            mutableImagesLiveData.postValue(emptyList())
            return
        }
        viewModelScope.launch {
            val searchResponse = RetrofitInstance.client.fetchImages(searchTerm)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutableImagesLiveData.postValue(photosList)
        }
    }

    fun fetchImages2(searchTerm: String) {
        if (searchTerm.isBlank()) {
            mutableImagesLiveData.postValue(emptyList())
            return
        }
        val searchResponse = RetrofitInstance.client.fetchImages2(searchTerm)
        searchResponse.enqueue(object : Callback<ImageSearchResponse> {
            override fun onResponse(
                call: Call<ImageSearchResponse>,
                response: Response<ImageSearchResponse>
            ) {
                var body = response.body()
                val photosList = body?.photos?.photo?.map { photo ->
                    Photo(
                        id = photo.id,
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = photo.title
                    )
                }
                mutableImagesLiveData.postValue(photosList)
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<ImageSearchResponse>, throwable: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }


    fun fetchImages3(searchTerm: String) {
        if (searchTerm.isBlank()) {
            mutableImagesLiveData.postValue(emptyList())
            return
        }
        val apiInterface = RetrofitBuilder.getInstance().getApiInterface()
        val call = apiInterface.fetchImages(searchTerm)

        call.enqueue(object : Callback<ImageSearchResponse> {
            override fun onResponse(
                call: Call<ImageSearchResponse>,
                response: Response<ImageSearchResponse>
            ) {
                var body = response.body()
                val photosList = body?.photos?.photo?.map { photo ->
                    Photo(
                        id = photo.id,
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = photo.title
                    )
                }
                mutableImagesLiveData.postValue(photosList)
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<ImageSearchResponse>, throwable: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }

}
