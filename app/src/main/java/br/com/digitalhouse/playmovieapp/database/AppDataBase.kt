package br.com.digitalhouse.playmovieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.dao.GenreDAO

@Database(entities = [Genre::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun genreDAO(): GenreDAO

    companion object DatabaseBuilder {
        private var instance: AppDataBase? = null

        @JvmStatic
        fun getAppDatabase(context: Context) = instance ?: build(context)
        private val LOCK = Any()
        private fun build(context: Context): AppDataBase {
            val dataBase = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "play_movie_app.db"
            )
            dataBase.allowMainThreadQueries()
            val appDataBase = dataBase.build()
            instance = appDataBase
            return appDataBase
        }

        @JvmStatic
        fun destroyInstance() {
            instance = null
        }

    }
}
