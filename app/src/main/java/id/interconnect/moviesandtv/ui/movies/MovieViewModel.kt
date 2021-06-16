package id.interconnect.moviesandtv.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.vo.Resource

class MovieViewModel(private val repository: MovieTVRepository) : ViewModel() {
    private val detailMovieId = MutableLiveData<Int>()

    fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieItemEntity>>> {
        return repository.getPopularMovies(sort)
    }

    var detailMovie: LiveData<Resource<MovieItemEntity>> =
        Transformations.switchMap(detailMovieId) { movieId ->
            repository.getDetailMovie(movieId)
        }

    fun setSelectedMovieItem(id: Int) {
        this.detailMovieId.value = id
    }

    fun setFavorite() {
        val movieItem = detailMovie.value
        if (movieItem != null) {
            val movieItemData = movieItem.data
            if (movieItemData != null) {
                val newState = !movieItemData.favorited
                repository.setFavoriteMovie(movieItemData, newState)
            }
        }
    }
}
