package id.interconnect.moviesandtv.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity

class FavoriteTVViewModel(private val movieTVRepository: MovieTVRepository) : ViewModel() {
    fun getFavoriteTV(): LiveData<PagedList<TVItemEntity>> {
        return movieTVRepository.getFavoriteTV()
    }

    fun setFavorite(tvItemEntity: TVItemEntity) {
        val newstate = !tvItemEntity.favorited
        movieTVRepository.setFavoriteTv(tvItemEntity, newstate)
    }
}