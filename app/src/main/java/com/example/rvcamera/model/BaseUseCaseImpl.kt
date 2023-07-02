package com.example.rvcamera.model

import com.example.rvcamera.data.NewsTopHeadingModel

class BaseUseCaseImpl(private  val newsRepository: BaseRepository) : BaseUseCase{
    override suspend fun getTopHeading(country : String, category: String, apiKey : String): Result<NewsTopHeadingModel> {
       return newsRepository.getTopHeading(country= country , category = category, apiKey = apiKey)
    }
}