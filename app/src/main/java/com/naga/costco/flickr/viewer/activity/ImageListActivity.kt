package com.naga.costco.flickr.viewer.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.Observer

import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter
import com.naga.costco.flickr.viewer.viewmodel.ImageListViewModel
import kotlinx.android.synthetic.main.activity_image_list.*
import java.lang.Exception

class ImageListActivity : AppCompatActivity() {

    private val TAG = ImageListActivity::class.java.simpleName
    private val imagesViewModel: ImageListViewModel by viewModels()
    private val imagesAdapter = ImageRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        try {
            photosRecyclerView.adapter = imagesAdapter
            photosRecyclerView.layoutManager = GridLayoutManager(this, 3)

            photosRecyclerView.adapter = imagesAdapter
            photosRecyclerView.layoutManager = GridLayoutManager(this, 3)
            imagesViewModel.photosLiveData.observe(this,
                Observer { list ->
                    with(imagesAdapter) {
                        photos.clear()
                        photos.addAll(list)
                        notifyDataSetChanged()
                    }
                })
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: " + e.message)
        }
    }

    fun searchOnclick(view: View) {
        hideKeyboard(view)
        val editTextHello = searchBox.text.toString()
        imagesViewModel.fetchImages3(editTextHello)
    }

    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}