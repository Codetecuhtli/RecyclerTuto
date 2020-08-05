package com.codetecuhtli.recyclertuto.movie.model

import com.codetecuhtli.recyclertuto.model.Movie
import java.lang.Exception

sealed class MovieResult

object Loading: MovieResult()

class MoviesSuccess(val movies: List<Movie>): MovieResult()

class MoviesError(val error: Exception): MovieResult()

class MovieSuccess(val movie: Movie): MovieResult()

class MovieError(val error: Exception): MovieResult()

