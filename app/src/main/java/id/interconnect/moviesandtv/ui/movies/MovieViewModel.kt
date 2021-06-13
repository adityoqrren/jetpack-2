package id.interconnect.moviesandtv.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.MovieItem
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.vo.Resource

class MovieViewModel(private val repository: MovieTVRepository) : ViewModel() {
    val detailMovieId = MutableLiveData<Int>()

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieItemEntity>>> {
        return repository.getPopularMovies()
    }

    var detailMovie: LiveData<Resource<MovieItemEntity>> = Transformations.switchMap(detailMovieId) {
       movieId -> repository.getDetailMovie(movieId)
    }

    fun setSelectedMovieItem(id: Int){
        this.detailMovieId.value = id
    }

    fun setFavorite(){
        val movieItem = detailMovie.value
//        Log.d("check favorite vm: ",detailTVId..toString())
        if(movieItem != null){
            val movieItemData = movieItem.data
            Log.d("check favorite vm: ",movieItemData.toString())
            if(movieItemData != null){
                Log.d("check favorite vm: ",movieItemData.favorited.toString())
                val newState = !movieItemData.favorited
                repository.setFavoriteMovie(movieItemData, newState)
            }
        }
    }

}
