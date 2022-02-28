package com.mustafasuleymankinik.spacetraveler.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by mustafasuleymankinik on 28.02.2022.
 */
@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 1,
    var name: String? = "Süleyman",
    var ugs: Int? = 50000,
    var eus: Int? = 100,
    var ds: Int? = 50000,
    @ColumnInfo(name = "ship_damage")
    var shipDamage: Int? = 100,
    var userPlanetName:String? = "Dünya",
    @ColumnInfo(name = "user_coordinate_x")
    var userCoordinateX: Double? = 0.0,
    @ColumnInfo(name = "user_coordinate_y")
    var userCoordinateY: Double? = 0.0,
)
