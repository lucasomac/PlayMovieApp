package br.com.digitalhouse.playmovieapp.database
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//import br.com.digitalhouse.playmovieapp.domain.movie.Genre
//import java.sql.SQLException
//
//class DataBaseHandler(context: Context) :
//    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//    companion object {
//        private val DATABASE_NAME = "play_movie_app_base"
//        private val DATABASE_VERSION = 1
//
//        //Constantes table gastos
//        private val TABLE_GENRE = "Genre"
//        private val KEY_ID = "id"
//        private val KEY_NAME = "name"
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val create_table_gastos =
//            "CREATE TABLE $TABLE_GENRE($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT)"
//        db?.execSQL(create_table_gastos)
//    }
//
//
//    fun addGenre(genre: Genre): Long {
//        val db = this.writableDatabase
//        val content = ContentValues()
//        content.put(KEY_ID, genre.id)
//        content.put(KEY_NAME, genre.name)
//        val res = db.insert(TABLE_GENRE, null, content)
//        db.close()
//        return res
//    }
//
//    fun deleteGenre(genre: Genre): Int {
//        val db = this.readableDatabase
//        val content = ContentValues()
//        content.put(KEY_ID, genre.id)
//        content.put(KEY_NAME, genre.name)
//        val res = db.delete(TABLE_GENRE, "id = ?", arrayOf("${genre.id}"))
//        db.close()
//        return res
//    }
//
//    fun getAllGenres(): ArrayList<Genre> {
//        var listGastos = ArrayList<Genre>()
//        val query = "SELECT * from $TABLE_GENRE"
//        val database = this.readableDatabase
//        val cursor: Cursor?
//        try {
//            cursor = database.rawQuery(query, null)
//            if (cursor.moveToFirst()) {
//                do {
//                    var id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
//                    var name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
//                    listGastos.add(Genre(id, name))
//                } while (cursor.moveToNext())
//            }
//        } catch (e: SQLException) {
//            Log.i("DATABASE_ERROR", e.toString())
//        }
//        return listGastos
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS $TABLE_GENRE")
//    }
//}