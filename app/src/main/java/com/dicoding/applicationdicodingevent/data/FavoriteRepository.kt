package com.dicoding.applicationdicodingevent.data

import androidx.lifecycle.LiveData
import com.dicoding.applicationdicodingevent.data.local.entity.FavoriteEvent
import com.dicoding.applicationdicodingevent.data.local.room.EventDao

class FavoriteRepository(private val favoriteEventDao: EventDao) {
    suspend fun insert(favoriteEvent: FavoriteEvent) {
        favoriteEventDao.insertFavorite(favoriteEvent)
    }
    fun getAllFavorites(): LiveData<List<FavoriteEvent>> {
        return favoriteEventDao.getAllFavoritesLiveData()
    }
    suspend fun insertFavorite(event: FavoriteEvent) {
        favoriteEventDao.insertFavorite(event)
    }
    suspend fun deleteFavorite(event: FavoriteEvent) {
        favoriteEventDao.deleteFavorite(event)
    }
    suspend fun deleteFavoriteById(id: String) {
        return favoriteEventDao.deleteFavoriteById(id)
    }
}