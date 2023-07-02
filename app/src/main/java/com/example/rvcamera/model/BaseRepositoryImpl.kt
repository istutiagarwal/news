package com.example.rvcamera.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rvcamera.data.NewsTopHeadingModel
import com.example.rvcamera.source.BaseService
import com.example.rvcamera.source.RetrofitHelper

class BaseRepositoryImpl() {
    suspend fun getTopHeading(country: String, category: String, apiKey: String)  =
        RetrofitHelper.api.getTopHeading(country = country, category = category, apiKey = apiKey)

}