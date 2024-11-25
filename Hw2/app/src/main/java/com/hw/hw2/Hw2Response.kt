package com.hw.hw2

data class Hw2Response(val data: List<GifObject>)
data class GifObject(val images: GifImages)
data class GifImages(val original: GifImage)
data class GifImage(val url: String)
