package com.example.a3_education_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteExoplanet::class], version = 1, exportSchema = false)
abstract class SpaceEducationDatabase : RoomDatabase() {
    abstract fun favoriteExoplanetDao(): FavoriteExoplanetDao

    companion object {
        @Volatile private var Instance: SpaceEducationDatabase? = null

        fun getDatabase(context: Context): SpaceEducationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    SpaceEducationDatabase::class.java,
                    "space_education_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}