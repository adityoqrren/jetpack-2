package id.interconnect.moviesandtv.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.data.TVItem
import id.interconnect.moviesandtv.databinding.TvListItemBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback

class TVListAdapter(val onClickItemCallback: OnClickItemCallback) :
    RecyclerView.Adapter<TVListAdapter.MyViewHolder>() {
    var tvList = emptyList<TVItem>()

    inner class MyViewHolder(private val binding: TvListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVItem) {
            with(binding) {
                tvitemJudul.text = tv.original_name
                tvitemRating.text = tv.vote_average.toString()
                tvitemOverview.text = tv.overview
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185/${tv.poster_path}")
                    .into(tvitemImg)
                tvCardview.setOnClickListener {
                    onClickItemCallback.onitemClick(tv.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = TvListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tvList[position])
    }


}