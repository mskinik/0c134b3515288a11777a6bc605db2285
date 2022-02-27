package com.mustafasuleymankinik.spacetraveler.repo.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mustafasuleymankinik.spacetraveler.model.Planet

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
@androidx.room.Database(entities = [Planet::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): Dao

    companion object {
        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    "my_db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            }
            return instance as Database
        }
    }
}