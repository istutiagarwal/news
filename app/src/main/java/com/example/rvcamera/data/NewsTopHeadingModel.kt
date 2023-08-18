package com.example.rvcamera.data

import com.google.gson.annotations.SerializedName


data class NewsTopHeadingModel(
    @SerializedName("articles")
    val article : MutableList<ArticlesModel>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int

)
