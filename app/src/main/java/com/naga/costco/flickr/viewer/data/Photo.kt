package com.naga.costco.flickr.viewer.data

import java.io.Serializable

data class Photo(
    val id: String,
    val url: String,
    val title: String
) : Serializable
