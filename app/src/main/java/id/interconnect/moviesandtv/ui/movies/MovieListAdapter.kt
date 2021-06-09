package id.interconnect.moviesandtv.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.MovieListItemBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback


class MovieListAdapter(private val itemClick: OnClickItemCallback) :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    var movieList = emptyList<id.interconnect.moviesandtv.data.MovieItem>()

    inner class MyViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: id.interconnect.moviesandtv.data.MovieItem) {
            with(binding) {
                movieitemJudul.text = movie.title
                movieitemOverview.text = movie.overview
                movieitemRating.text = movie.vote_average.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieitemImg)
                movieCardview.setOnClickListener {
                    itemClick.onitemClick(movie.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}