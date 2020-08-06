package com.codetecuhtli.recyclertuto.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.codetecuhtli.recyclertuto.R
import com.codetecuhtli.recyclertuto.core.adapter.MovieAdapter
import com.codetecuhtli.recyclertuto.movie.MovieViewModel
import com.codetecuhtli.recyclertuto.movie.model.Loading
import com.codetecuhtli.recyclertuto.movie.model.MoviesError
import com.codetecuhtli.recyclertuto.movie.model.MoviesSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    @Inject lateinit var movieAdapter:  MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        initView()
        initObservables()
    }

    private fun initView(){
        moviesRecycler.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initObservables(){
        movieAdapter.onClick = {
            Log.i(MainActivity::class.java.simpleName, "$it")
        }

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