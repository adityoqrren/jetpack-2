package id.interconnect.moviesandtv.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.data.dataentity.MovieItem
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListAdapter(val itemClick : OnClickItemCallback) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>(){
    var movieList = emptyList<id.interconnect.moviesandtv.data.MovieItem>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(movie: id.interconnect.moviesandtv.data.MovieItem){
            with(itemView){
                movieitem_judul.text = movie.title
                movieitem_overview.text = movie.overview
                movieitem_rating.text = movie.vote_average.toString()
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieitem_img)
                movie_cardview.setOnClickListener {
                    itemClick.onitemClick(movie.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item,parent,false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}