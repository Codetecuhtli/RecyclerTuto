package com.codetecuhtli.recyclertuto.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.codetecuhtli.recyclertuto.movie.model.*
import com.codetecuhtli.recyclertuto.network.MovieApi
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieRepository(private val movieApi: MovieApi) {

    fun getMovies(): LiveData<MovieResult> = liveData(Dispatchers.IO) {

        emit(Loading)

        try {
            emit(MoviesSuccess(movieApi.getMovies()))
        }catch (e: Exception){
            emit(MoviesError(e))
        }

    }

    fun getMovie(id: Int): LiveData<MovieResult> = liveData(Dispatchers.IO){

        emit(Loading)

        try {
            emit(MovieSuccess(movieApi.getMovie(id)))
        }catch (e: Exception){
            emit(MovieError(e))
        }
    }

}