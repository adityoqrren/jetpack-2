package id.interconnect.moviesandtv.ui.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.vo.Resource

class TVViewModel(private val repository: MovieTVRepository) : ViewModel() {
    //GANTI JADI ID AJA DISINI COBA
    val detailTVId = MutableLiveData<Int>()

    fun getPopularTV(): LiveData<Resource<PagedList<TVItemEntity>>> {
        return repository.getPopularTV()
    }

    fun setSelectedTVItem(id: Int){
        this.detailTVId.value = id
    }

    var detailTV: LiveData<Resource<TVItemEntity>> = Transformations.switchMap(detailTVId){
            tvId -> repository.getDetailTV(tvId)
    }

    fun setFavorite(){
        val tvItem = detailTV.value
//        Log.d("check favorite vm: ",detailTVId..toString())
        if(tvItem != null){
            val tvItemData = tvItem.data
            Log.d("check favorite vm: ",tvItemData.toString())
            if(tvItemData != null){
                Log.d("check favorite vm: ",tvItemData.favorited.toString())
                val newState = !tvItemData.favorited
                repository.setFavoriteTv(tvItemData, newState)
            }
        }
    }

}