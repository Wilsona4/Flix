package com.example.core_database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.core_database.model.PopularMovieEntity

@Dao
interface PopularDao {

    /*Add Movie to Database*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(user: List<PopularMovieEntity>)

    /*Get Movie in the Database*/
    @Transaction
    @Query("SELECT * FROM popular_movies")
    fun readMovies(): PagingSource<Int, PopularMovieEntity>

    /*Delete Movie in the Database*/
    @Query("DELETE FROM popular_movies")
    suspend fun clearMovies()
}