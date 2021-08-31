package com.naga.costco.flickr.viewer.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter
import com.naga.costco.flickr.viewer.data.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_details.*

class ImageDetailsFragment : Fragment(), OnClickListener {

    lateinit var photos: Photo
    private val TAG = ImageDetailsFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photos = requireArguments().getParcelable("images")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.back_button).setOnClickListener(this)
        view.findViewById<TextView>(R.id.title).setOnClickListener(this)

        try {
            Picasso.get().load(photos.url)
                .resize(
                    ImageRecyclerViewAdapter.PhotosViewHolder.IMAGE_SIDE_PX,
                    ImageRecyclerViewAdapter.PhotosViewHolder.IMAGE_SIDE_PX
                )
                .centerCrop()
                .into(photoImageView)
            photoDescription?.text = photos.title
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: " + e.message)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_details, container, false)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back_button -> requireActivity().onBackPressed()
            R.id.title -> requireActivity().onBackPressed()
        }
    }
}