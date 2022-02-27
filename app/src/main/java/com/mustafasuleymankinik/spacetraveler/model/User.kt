package com.mustafasuleymankinik.spacetraveler.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by mustafasuleymankinik on 28.02.2022.
 */
@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 1,
    var name: String?,
    var ugs: Int?,
    var eus: Int?,
    var ds: Int?,
    var planet: Planet = Planet(
        id = 1L,
        name = "Dünya",
        coordinateX = 0.0,
        coordinateY = 0.0,
        capacity = 0,
        stock = 0,
        need = 0,
        favorite = false,
        eus = null
    )
)
