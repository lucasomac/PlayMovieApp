package br.com.digitalhouse.playmovieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.digitalhouse.playmovieapp.dao.GenreDAO
import br.com.digitalhouse.playmovieapp.domain.Genre

@Database(entities = [Genre::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun genreDAO(): GenreDAO

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "play_movie_app.db"
        ).build()
    }
}