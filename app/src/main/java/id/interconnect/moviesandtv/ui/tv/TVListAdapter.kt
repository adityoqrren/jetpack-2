package id.interconnect.moviesandtv.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.data.TVItem
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import kotlinx.android.synthetic.main.tv_list_item.view.*

class TVListAdapter(val onClickItemCallback: OnClickItemCallback) : RecyclerView.Adapter<TVListAdapter.MyViewHolder>(){
    var tvList = emptyList<TVItem>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(tv: TVItem){
            with(itemView){
                tvitem_judul.text = tv.original_name
                tvitem_rating.text = tv.vote_average.toString()
                tvitem_overview.text = tv.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185/${tv.poster_path}")
                    .into(tvitem_img)
                tv_cardview.setOnClickListener {
                    onClickItemCallback.onitemClick(tv.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.tv_list_item,parent,false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tvList[position])
    }


}