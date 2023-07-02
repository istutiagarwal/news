package com.example.rvcamera.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rvcamera.data.NewsTopHeadingModel
import com.example.rvcamera.model.BaseRepository
import com.example.rvcamera.model.BaseRepositoryImpl
import com.example.rvcamera.model.BaseUseCase
import com.example.rvcamera.resources.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class BaseViewModel(private val  repository: BaseRepositoryImpl) : ViewModel() {

    val _topHeadingLiveData : MutableLiveData<Resources<NewsTopHeadingModel>> = MutableLiveData()
    val topHeadingLiveData : LiveData<Resources<NewsTopHeadingModel>> = _topHeadingLiveData

    init {
        getTopHeading()
    }

    fun getTopHeading() {
        viewModelScope.launch(Dispatchers.IO) {
          val response =   repository.getTopHeading(
                country = "us",
                category = "business",
                apiKey = "fb63fbba81b248d09cdb6a4e8768a0d6"
            )
            _topHeadingLiveData.postValue(handleTopHeading(response = response))
        }
    }

    private  fun handleTopHeading(response: Response<NewsTopHeadingModel>) : Resources<NewsTopHeadingModel>{
        if(response.isSuccessful){
            response.body()?.let {
               return Resources.Success(it)
            }
        }
        return Resources.Error(null, response.message())
    }
}
