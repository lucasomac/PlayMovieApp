package br.com.digitalhouse.playmovieapp.services

import br.com.digitalhouse.playmovieapp.dao.GenreDAO
import br.com.digitalhouse.playmovieapp.domain.Genre


interface RepositorySqlite {
    suspend fun insertGenreTask(genre: Genre): List<Genre>
    suspend fun selectAllGenreTask(): List<Genre>
    suspend fun deleteGenreTask(genre: Genre): List<Genre>
}

class RepositorySqliteImplementation(val genreDAO: GenreDAO) : RepositorySqlite {

    override suspend fun insertGenreTask(genre: Genre): List<Genre> {
        genreDAO.create(genre)
        return genreDAO.read()
    }

    override suspend fun selectAllGenreTask(): List<Genre> {
        return genreDAO.read()
    }

    override suspend fun deleteGenreTask(genre: Genre): List<Genre> {
        genreDAO.delete(genre)
        return genreDAO.read()
    }
}