package id.interconnect.moviesandtv.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.MovieTVRepository

class MovieViewModel(private val repository: MovieTVRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<List<id.interconnect.moviesandtv.data.MovieItem>> {
        return repository.getPopularMovies()
    }

    fun getDetailMovie(id: Int): LiveData<DetailMovie> {
        return repository.getDetailMovie(id)
    }
}
