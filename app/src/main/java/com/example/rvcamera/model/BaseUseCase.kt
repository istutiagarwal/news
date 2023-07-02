package com.example.rvcamera.model

import com.example.rvcamera.data.NewsTopHeadingModel

interface BaseUseCase  {

    suspend fun getTopHeading(country : String, category : String, apiKey : String) : Result<NewsTopHeadingModel>
}