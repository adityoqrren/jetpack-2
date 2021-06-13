package id.interconnect.moviesandtv.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.data.TVItem
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.databinding.TvListItemBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback

class TVListAdapter(val onClickItemCallback: OnClickItemCallback) :
    PagedListAdapter<TVItemEntity, TVListAdapter.MyViewHolder>(DIFF_CALLBACK) {
//    var tvList = emptyList<TVItem>()

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<TVItemEntity>(){
            override fun areContentsTheSame(oldItem: TVItemEntity, newItem: TVItemEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TVItemEntity, newItem: TVItemEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    inner class MyViewHolder(private val binding: TvListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVItemEntity) {
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

//    override fun getItemCount(): Int {
//        return tvList.size
//    }

    fun getSwipedData(swippedPosition: Int): TVItemEntity? = getItem(swippedPosition)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tvItem = getItem(position)
        tvItem?.let { holder.bind(it) }
    }


}