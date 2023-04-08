package com.openwebinars.filmapp.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.FavoritesRepository
import com.openwebinars.filmapp.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel : ViewModel() {

    val isFavorite = MutableLiveData<Boolean>()
    private val favoritesRepository: FavoritesRepository = FavoritesRepository()

    fun onViewCreated(film: Film?) {
        film?.let {
            viewModelScope.launch {
                val exist = favoritesRepository.exist(it)
                isFavorite.postValue(exist)
            }
        }
    }

    fun onClickFavorite(film: Film) {
        viewModelScope.launch {
            if (favoritesRepository.exist(film)) {
                favoritesRepository.remove(film)
                isFavorite.postValue(false)
            } else {
                favoritesRepository.save(film)
                isFavorite.postValue(true)
            }
        }
    }
}