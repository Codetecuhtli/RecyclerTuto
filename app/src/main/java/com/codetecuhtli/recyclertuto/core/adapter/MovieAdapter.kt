package com.codetecuhtli.recyclertuto.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetecuhtli.recyclertuto.R
import com.codetecuhtli.recyclertuto.core.loadImage
import com.codetecuhtli.recyclertuto.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.card_movie.view.*
import javax.inject.Inject

class MovieAdapter @Inject constructor(@ApplicationContext private val context: Context):
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var movies: MutableList<Movie> = mutableListOf()
    set(value) {
        field.let {
            it.clear()
            it.addAll(value)
        }
        notifyDataSetChanged()
    }

    var onClick: ((movie: Movie) -> Unit)? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.card_movie, parent, false), onClick)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieHolder(private val view: View,
                            private val onClick: ((movie: Movie) -> Unit)?): RecyclerView.ViewHolder(view){

        fun bind(movie: Movie){
            onClick?.let {
                view.setOnClickListener { it(movie) }
            }
            view.movieTitle.text = movie.title
            view.movieDate.text = movie.releaseDate
            view.movieAverage.text = "${movie.voteAverage}"
            view.movieOverview.text = movie.overview
            view.movieImage.loadImage(movie.posterPath)
        }

    }

}