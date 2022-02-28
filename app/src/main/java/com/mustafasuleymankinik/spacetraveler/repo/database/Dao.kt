package com.mustafasuleymankinik.spacetraveler.repo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.model.User
import retrofit2.http.GET

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */

@Dao
interface Dao {
    @Query("SELECT * FROM table_planet ORDER BY id ASC")
    fun getPlanetList(): LiveData<List<Planet>>

    @Query("UPDATE table_planet SET favorite=:favorite WHERE id=:id")
    fun updateFavorite(id: Long, favorite: Boolean?)

    @Query("UPDATE table_planet SET need=:need,stock=:stock,travelled=:travelled WHERE id=:id")
    fun updatePlanetInfo(id: Long,need: Int, stock: Int,travelled:Boolean)

    @Insert()
    fun addPlanet(planet: Planet): Long

    @Query("SELECT * FROM table_planet WHERE favorite=1 ORDER BY id ASC ")
    fun getFavoritedList(): List<Planet>

    @Insert
    fun addUser(user: User):Long
    @Update
    fun updateUser(user: User):Int

    @Query("SELECT * FROM table_user")
    fun getUser():LiveData<User>
}