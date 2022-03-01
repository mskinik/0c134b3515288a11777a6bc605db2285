package com.mustafasuleymankinik.spacetraveler.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.mustafasuleymankinik.spacetraveler.model.User
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.repo.database.Database

/**
 * Created by mustafasuleymankinik on 28.02.2022.
 */
class UserViewModel(val app: Application) : AndroidViewModel(app) {
    var dao: Dao = Database.getInstance(app).noteDao()

    val _durability = MutableLiveData<String>()
    val durability: LiveData<String> get() = _durability

    val _speed = MutableLiveData<String>()
    val speed: LiveData<String> get() = _speed

    val _capacity = MutableLiveData<String>()
    val capability: LiveData<String> get() = _capacity

    val name = MutableLiveData<String>()

    val _success = MutableLiveData<Boolean>()
    val success:LiveData<Boolean> get() = _success

    init {
        if (dao.getUserForCheck(1) == 1L)
            _success.value = true
    }

    fun save() {
        val speed = _speed.value
        val durability = _durability.value
        val capability = _capacity.value
        if (!speed.isNullOrEmpty() && !durability.isNullOrEmpty() && !capability.isNullOrEmpty()) {

            if (speed.toInt() in 1..13 && capability.toInt() in 1..13 && durability.toInt() in 1..13) {
                if (speed.toInt() + capability.toInt() + durability.toInt() == 15) {
                    if (!name.value.isNullOrEmpty()) {
                        val value = dao.addUser(
                            User(
                                name = name.value,
                                eus = (speed.toInt().times(20)),
                                ds = durability.toInt().times(10000),
                                ugs = capability.toInt().times(10000)
                            )
                        )
                        if (value == 1L)
                            _success.value = true
                    }
                }
            }
        }
    }
}