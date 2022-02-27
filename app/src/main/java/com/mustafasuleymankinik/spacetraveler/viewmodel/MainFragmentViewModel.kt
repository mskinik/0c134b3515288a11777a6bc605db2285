package com.mustafasuleymankinik.spacetraveler.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.repo.database.Database
import com.mustafasuleymankinik.spacetraveler.repo.network.GenericObserver
import com.mustafasuleymankinik.spacetraveler.repo.network.NetworkCall

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
class MainFragmentViewModel(val app: Application) : AndroidViewModel(app) {
    private val _planetList = MutableLiveData<List<Planet>>()
    val planetList: LiveData<List<Planet>> get() = _planetList
    var dao: Dao? = null

    init {
        dao = Database.getInstance(app).noteDao()
        val list = dao?.getNoteList()
        if (list == null || list.isEmpty()) {
            NetworkCall.getList(object : GenericObserver<List<Planet>>() {
                override fun onNext(t: List<Planet>) {
                    t.forEach { planet ->
                        // temporary code line
                        planet.eus = 999.toString()
                        dao?.addPlanet(planet)
                    }
                    _planetList.value = dao?.getNoteList()
                }

                override fun onError(e: Throwable) {
                    // Do nothing for now
                }
            })
        } else {
            list.let {
                _planetList.value = it
            }
        }
    }

    fun updatePlanetFavorite(id: Long, favorite: Boolean) {
        dao?.updateFavorite(id, !favorite)
        _planetList.value = dao?.getNoteList()
    }
}