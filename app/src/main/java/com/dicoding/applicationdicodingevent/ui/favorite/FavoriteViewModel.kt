package com.dicoding.applicationdicodingevent.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.applicationdicodingevent.data.FavoriteRepository
import com.dicoding.applicationdicodingevent.data.local.entity.FavoriteEvent
import kotlinx.coroutines.launch


class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun insertFavorite(favoriteEvent: FavoriteEvent) {
        viewModelScope.launch {
            repository.insert(favoriteEvent)
        }
    }
    fun getFavoriteEvents(): LiveData<List<FavoriteEvent>> {
        _isLoading.value = true
        val favorites = repository.getAllFavorites()
        favorites.observeForever {
            _isLoading.value = false
        }
        return favorites
    }
    suspend fun deleteFavoriteById(id: String) {
        return repository.deleteFavoriteById(id)
    }
}