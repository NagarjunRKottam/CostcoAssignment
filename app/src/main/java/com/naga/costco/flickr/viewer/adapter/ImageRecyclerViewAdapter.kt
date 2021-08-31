package com.naga.costco.flickr.viewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.naga.costco.flickr.viewer.R
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

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

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
            val navController = Navigation.findNavController(v)
            if (photo != null) {
                val bundle = bundleOf(
                    IMAGES to photo,
                )
                navController.navigate(
                    R.id.action_imageListFragment_to_imageDetailsFragment,
                    bundle
                )
            } else {
                Toast.makeText(itemView.context, IMAGE_LIST_EMPTY, Toast.LENGTH_SHORT).show()
            }
        }

        companion object {
            const val IMAGES = "images"
            const val IMAGE_SIDE_PX = 400
            const val IMAGE_LIST_EMPTY = "Image list is empty"
        }
    }
}


