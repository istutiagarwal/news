package com.example.rvcamera.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rvcamera.model.BaseRepository
import com.example.rvcamera.model.BaseRepositoryImpl
import com.example.rvcamera.model.BaseUseCase

class BaseViewModelFactory(private val repository: BaseRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseViewModel(repository) as T
    }

}