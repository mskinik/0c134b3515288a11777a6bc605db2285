package com.mustafasuleymankinik.spacetraveler.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
@Entity(tableName = "table_planet")
data class Planet(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String?,
    @ColumnInfo(name = "coordinate_x")
    var coordinateX: Double?,
    @ColumnInfo(name = "coordinate_y")
    var coordinateY: Double?,
    var capacity: Int?,
    var stock: Int?,
    var need: Int?,
    var favorite:Boolean = false,
    var distanceToEarth:Double?,
    var travelled:Boolean = false
)
