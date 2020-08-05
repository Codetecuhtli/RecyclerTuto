package com.codetecuhtli.recyclertuto.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.codetecuhtli.recyclertuto.movie.model.MovieResult

class MovieViewModel(private val repository: MovieRepository): ViewModel() {

    private val movieIdInput = MutableLiveData<Int>()

    val movie: LiveData<MovieResult> by lazy {
        Transformations.switchMap(movieIdInput) { id ->
            repository.getMovie(id)
        }
    }

    val movies: LiveData<MovieResult> by lazy {
        repository.getMovies()
    }

    fun getMovie(id: Int) {
        movieIdInput.value = id
    }

}