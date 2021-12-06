package br.com.digitalhouse.playmovieapp.services

import android.util.Log
import br.com.digitalhouse.playmovieapp.dao.GenreDAO
import br.com.digitalhouse.playmovieapp.domain.Genre


interface RepositoryRoom {
    suspend fun insertGenreTask(genre: Genre): List<Genre>
    suspend fun selectAllGenreTask(): List<Genre>
    suspend fun deleteGenreTask(genre: Genre): List<Genre>
}

class RepositoryRoomImplementation(private val genreDAO: GenreDAO) : RepositoryRoom {

    override suspend fun insertGenreTask(genre: Genre): List<Genre> {
        genreDAO.create(genre)
        Log.i("ROOM", genreDAO.read().toString())
        return genreDAO.read()
    }

    override suspend fun selectAllGenreTask(): List<Genre> {
        return genreDAO.read()
    }

    override suspend fun deleteGenreTask(genre: Genre): List<Genre> {
        genreDAO.delete(genre)
        Log.i("ROOM", genreDAO.read().toString())
        return genreDAO.read()
    }
}