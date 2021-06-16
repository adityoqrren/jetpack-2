package id.interconnect.moviesandtv.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.vo.Resource

interface MovieTVDataSource {
    //TV
    fun getPopularTV(): LiveData<Resource<PagedList<TVItemEntity>>>

    fun getDetailTV(id: Int): LiveData<Resource<TVItemEntity>>

    fun getFavoriteTV(): LiveData<PagedList<TVItemEntity>>

    fun setFavoriteTv(tvItemEntity: TVItemEntity, state: Boolean)

    //Movie
    fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieItemEntity>>>

    fun getDetailMovie(id: Int): LiveData<Resource<MovieItemEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieItemEntity>>

    fun setFavoriteMovie(movieItemEntity: MovieItemEntity, state: Boolean)
}