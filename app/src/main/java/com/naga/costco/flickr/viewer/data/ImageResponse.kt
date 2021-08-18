package com.naga.costco.flickr.viewer.data

data class ImageResponse(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)