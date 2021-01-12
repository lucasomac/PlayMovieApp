package br.com.digitalhouse.playmovieapp.dao

import androidx.room.*
import br.com.digitalhouse.playmovieapp.domain.Genre

@Dao
interface GenreDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(genre: Genre)

    @Query("SELECT * FROM genre")
    suspend fun read(): List<Genre>

    @Delete
    suspend fun delete(genre: Genre)
}