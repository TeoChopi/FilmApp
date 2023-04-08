package com.openwebinars.filmapp.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.FavRepository
import com.openwebinars.filmapp.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favRepository: FavRepository
) : ViewModel() {

    val isFavorite = MutableLiveData<Boolean>()

    fun onViewCreated(film: Film?) {
        film?.let {
            viewModelScope.launch {
                val exist = favRepository.exist(it)
                isFavorite.postValue(exist)
            }
        }
    }

    fun onClickFavorite(film: Film) {
        viewModelScope.launch {
            if (favRepository.exist(film)) {
                favRepository.remove(film)
                isFavorite.postValue(false)
            } else {
                favRepository.save(film)
                isFavorite.postValue(true)
            }
        }
    }
}