package com.example.rvcamera.model

import com.example.rvcamera.data.NewsTopHeadingModel
import com.example.rvcamera.source.BaseService

interface BaseRepository {

   suspend fun getTopHeading(country : String , category : String, apiKey : String) : Result<NewsTopHeadingModel>
}