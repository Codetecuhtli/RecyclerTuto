package com.codetecuhtli.recyclertuto.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codetecuhtli.recyclertuto.movie.model.MovieResult

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
): ViewModel() {

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