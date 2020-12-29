package com.example.popcom.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.popcom.R
import com.example.popcom.common.constants
import com.example.popcom.retrofit.models.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

class MovieRecyclerViewAdapter( /*values: List<Movie>*/)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private var movies : List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        holder.ivPhoto.load(constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true) //cuando se cargue que se difumine
            //placeholder(R.drawable.ic_cine)
            transformations(CircleCropTransformation())
        }

//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val idView: TextView = view.findViewById(R.id.item_number)
//        val contentView: TextView = view.findViewById(R.id.content)
        val ivPhoto : ImageView = view.ivPhoto
        val tvTitle : TextView = view.tvTitle
        val tvRating : TextView = view.tvRating

        override fun toString(): String {
            return super.toString() //+ " '" + contentView.text + "'"
        }
    }

    fun setData(popularMovies: List<Movie>?){
        movies = popularMovies!!
        notifyDataSetChanged()
    }
}