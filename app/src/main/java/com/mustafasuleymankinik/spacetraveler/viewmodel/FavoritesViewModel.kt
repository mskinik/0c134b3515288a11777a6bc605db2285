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
    private val _favoriteLiveData = MutableLiveData<List<Planet>>()
    val favoriteLiveData:LiveData<List<Planet>> get() = _favoriteLiveData
    init {
        getList()
    }

    fun updateFavorite(id:Long,favorite:Boolean){
        dao.updateFavorite(id,!favorite)
        getList()

    }
    fun getList(){
        _favoriteLiveData.value = dao.getFavoritedList()
    }
}