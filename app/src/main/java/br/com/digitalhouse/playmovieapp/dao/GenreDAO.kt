package br.com.digitalhouse.playmovieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import br.com.digitalhouse.playmovieapp.domain.Genre

@Dao
interface GenreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(genre: Genre)

    @Query("SELECT * FROM genre")
    fun read(): List<Genre>

    @Delete
    fun delete(genre: Genre)
}