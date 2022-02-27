package com.mustafasuleymankinik.spacetraveler.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafasuleymankinik.spacetraveler.model.Planet

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */

@Dao
interface Dao {
    @Query("SELECT * FROM table_planet ORDER BY id ASC")
    fun getNoteList(): List<Planet>

    @Query("UPDATE table_planet SET favorite=:favorite WHERE id=:id")
    fun updateFavorite(id: Long, favorite: Boolean?)

    @Query("UPDATE table_planet SET eus=:eus,need=:need,stock=:stock WHERE id=:id")
    fun updatePlanetInfo(id: Long, eus: String, need: Int, stock: Int)

    @Insert()
    fun addPlanet(planet: Planet):Long

    @Query("SELECT * FROM table_planet WHERE favorite=1 ORDER BY id ASC ")
    fun getFavoritedList():List<Planet>
}