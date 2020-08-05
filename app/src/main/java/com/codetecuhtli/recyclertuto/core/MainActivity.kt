package com.codetecuhtli.recyclertuto.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.codetecuhtli.recyclertuto.R
import com.codetecuhtli.recyclertuto.core.adapter.MovieAdapter
import com.codetecuhtli.recyclertuto.movie.MovieRepository
import com.codetecuhtli.recyclertuto.movie.MovieViewModel
import com.codetecuhtli.recyclertuto.movie.MovieViewModelFactory
import com.codetecuhtli.recyclertuto.movie.model.Loading
import com.codetecuhtli.recyclertuto.movie.model.MoviesError
import com.codetecuhtli.recyclertuto.movie.model.MoviesSuccess
import com.codetecuhtli.recyclertuto.network.MovieApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(MovieApi.instance))
    }

    private lateinit var movieAdapter:  MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        initInstances()
        initView()
        initObservables()
    }

    private fun initView(){
        moviesRecycler.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initInstances(){

        movieAdapter = MovieAdapter(applicationContext) {
            Log.i(MainActivity::class.java.simpleName, "$it")
        }

    }

    private fun initObservables(){
        movieViewModel.movies.observe(this) {
            when(it){
                is Loading -> {

                }
                is MoviesSuccess -> {
                    movieAdapter.movies = it.movies.toMutableList()
                }
                is MoviesError -> {

                }
            }
        }
    }
}