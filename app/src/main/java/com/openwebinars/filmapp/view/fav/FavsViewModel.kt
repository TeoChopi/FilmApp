package com.openwebinars.filmapp.view.fav

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.FavoritesRepository
import com.openwebinars.filmapp.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class FavsViewModel: ViewModel() {

    val favsLiveData = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()
    private val favsRepository: FavoritesRepository = FavoritesRepository()

    fun onViewCreated() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val favs = favsRepository.get()
            favsLiveData.postValue(favs)
            isLoading.postValue(false)
        }
    }
}