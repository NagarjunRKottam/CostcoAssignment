package com.naga.costco.flickr.viewer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.activity.ImageDetailsActivity
import com.naga.costco.flickr.viewer.data.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_image_list.view.*


class ImageRecyclerViewAdapter(val photos: MutableList<Photo> = mutableListOf()) :
    RecyclerView.Adapter<ImageRecyclerViewAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_image_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var photo: Photo? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(photo: Photo) {
            this.photo = photo
            Picasso.get().load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(itemView.imageView)
            itemView.image_title.text = photo.title
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, ImageDetailsActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo)
            context.startActivity(showPhotoIntent)
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
            const val IMAGE_SIDE_PX = 400
        }
    }
}


