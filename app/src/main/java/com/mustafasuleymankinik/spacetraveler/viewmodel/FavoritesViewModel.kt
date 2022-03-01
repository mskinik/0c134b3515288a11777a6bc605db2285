package com.mustafasuleymankinik.spacetraveler.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.repo.database.Database

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
class FavoritesViewModel(val app:Application):AndroidViewModel(app) {
    var dao:Dao = Database.getInstance(app).noteDao()

    val favoriteLiveData:LiveData<List<Planet>> = dao.getFavoritedList()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun updateFavorite(id:Long,favorite:Boolean){
        _isLoading.value = true
        dao.updateFavorite(id,!favorite)
        _isLoading.value = false
    }
}