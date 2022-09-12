package com.example.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_database.converter.Converter
import com.example.core_database.dao.PopularDao
import com.example.core_database.dao.RemoteKeysDao
import com.example.core_database.model.PopularMovieEntity
import com.example.core_database.model.RemoteKeys

@Database(
    entities = [PopularMovieEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class FlixDatabase : RoomDatabase() {

    abstract fun popularDao(): PopularDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        var FLIX_DATABASE = "flix_database"
    }
}