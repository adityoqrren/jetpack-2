package id.interconnect.moviesandtv.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.api.MovieTVRepository

class MovieViewModel(private val repository: MovieTVRepository) : ViewModel() {

    fun getPopularMovie() : LiveData<List<id.interconnect.moviesandtv.data.MovieItem>>{
        return repository.getPopularMovie()
    }

    fun getDetailMovie(id: Int): LiveData<DetailMovie> {
       return repository.getDetailMovie(id)
    }
}
