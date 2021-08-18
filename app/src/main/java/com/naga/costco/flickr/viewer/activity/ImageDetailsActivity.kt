package com.naga.costco.flickr.viewer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter.PhotosViewHolder.Companion.IMAGE_SIDE_PX
import com.naga.costco.flickr.viewer.data.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_details.*

class ImageDetailsActivity : AppCompatActivity() {

    private val TAG = ImageDetailsActivity::class.java.simpleName
    private var selectedPhoto: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        supportActionBar?.hide()

        try {
            selectedPhoto = intent.getSerializableExtra(PHOTO_KEY) as Photo
            Picasso.get().load(selectedPhoto!!.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(photoImageView)
            photoDescription?.text = selectedPhoto?.title
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: " + e.message)
        }
    }

    fun onBlackClick(view: View) {
        onBackPressed()
    }

    companion object {
        private const val PHOTO_KEY = "PHOTO"
    }
}