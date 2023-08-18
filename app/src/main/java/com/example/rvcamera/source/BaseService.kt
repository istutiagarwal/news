package com.example.rvcamera.source

import android.app.Service
import com.example.rvcamera.data.NewsTopHeadingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {
    @GET("/v2/top-headlines")
    suspend fun getTopHeading(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String,
        @Query("page") pageNumber: Int = 1,
    ):Response<NewsTopHeadingModel>

    @GET("v2/everything")
    suspend fun getSearchResults(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy")sortBy : String,
        @Query("apiKey") apiKey : String,
        @Query("page")
        pageNumber: Int = 1,
    ) : Response<NewsTopHeadingModel>
}