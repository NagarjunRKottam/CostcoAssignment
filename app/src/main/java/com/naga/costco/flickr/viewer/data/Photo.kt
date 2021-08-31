package com.naga.costco.flickr.viewer.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Photo(
    val id: String,
    val url: String,
    val title: String
) : Parcelable
