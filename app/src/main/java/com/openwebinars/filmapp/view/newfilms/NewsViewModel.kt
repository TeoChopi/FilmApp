package com.openwebinars.filmapp.view.newfilms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.Film
import com.openwebinars.filmapp.model.NewFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newFilmsRepository: NewFilmsRepository
) : ViewModel() {

    val newsLiveData = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getNews() {
        viewModelScope.launch {
            isLoading.postValue(true)
            newsLiveData.postValue(newFilmsRepository.get())
            isLoading.postValue(false)
        }
    }
}