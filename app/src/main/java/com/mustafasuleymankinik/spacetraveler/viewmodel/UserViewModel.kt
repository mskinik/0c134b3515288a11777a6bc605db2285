package com.mustafasuleymankinik.spacetraveler.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.repo.database.Database

/**
 * Created by mustafasuleymankinik on 28.02.2022.
 */
class UserViewModel(val app: Application) : AndroidViewModel(app) {
    var dao: Dao = Database.getInstance(app).noteDao()
    private val _durability = MutableLiveData<Int>()
    val durability: LiveData<Int> get() = _durability
    private val _speed = MutableLiveData<Int>()
    val speed: LiveData<Int> = _speed
    private val _capacity = MutableLiveData<Int>()
    val capacity: LiveData<Int> get() = _capacity
    //private val _totalLiveData: MediatorLiveData<Int> = MediatorLiveData<Int>.addSource(_capacity,_totalLiveDa)


}