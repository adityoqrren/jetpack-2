package id.interconnect.moviesandtv.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.databinding.MovieListItemBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.utils.DateUtil


class MovieListAdapter(private val itemClick: OnClickItemCallback) :
    PagedListAdapter<MovieItemEntity, MovieListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItemEntity>() {
            override fun areContentsTheSame(
                oldItem: MovieItemEntity,
                newItem: MovieItemEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(
                oldItem: MovieItemEntity,
                newItem: MovieItemEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    inner class MyViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieItemEntity) {
            with(binding) {
                movieitemJudul.text = movie.original_title
                movieitemOverview.text = movie.overview
                movieitemRating.text = movie.vote_average.toString()
                movieitemYear.text = DateUtil.dateStringGetYear(movie.release_date)
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

    fun getSwipedData(swippedPosition: Int): MovieItemEntity? = getItem(swippedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieItem = getItem(position)
        movieItem?.let { holder.bind(it) }
    }

}