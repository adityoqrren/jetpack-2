package id.interconnect.moviesandtv.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity

class FavoriteMoviesViewModel(private val movieTVRepository: MovieTVRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieItemEntity>> {
        return movieTVRepository.getFavoriteMovies()
    }

    fun setFavorite(movieItemEntity: MovieItemEntity) {
        val newstate = !movieItemEntity.favorited
        movieTVRepository.setFavoriteMovie(movieItemEntity, newstate)
    }
}