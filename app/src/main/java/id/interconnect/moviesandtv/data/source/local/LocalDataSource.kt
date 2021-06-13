package id.interconnect.moviesandtv.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.data.source.local.room.MovieTVDao

class LocalDataSource private constructor(private val mMovieTVDao: MovieTVDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieTVDao: MovieTVDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mMovieTVDao)
    }

    //TV
    fun getPopularTV(): DataSource.Factory<Int, TVItemEntity> = mMovieTVDao.getPopularTV()

    fun getDetailTV(id: Int): LiveData<TVItemEntity> = mMovieTVDao.getDetailTV(id)

    fun getFavoriteTV(): DataSource.Factory<Int, TVItemEntity> = mMovieTVDao.getFavoriteTV()

    fun insertPopularTV(listPopularTv: List<TVItemEntity>) = mMovieTVDao.insertPopularTV(listPopularTv)

    fun updateDetailTV(detailTV: TVItemEntity) = mMovieTVDao.updateDetailTV(detailTV)

    fun setFavoriteTV(itemTV: TVItemEntity, newState: Boolean){
        itemTV.favorited = newState
        mMovieTVDao.updateDetailTV(itemTV)
    }

    //Movie
    fun getPopularMovies(): DataSource.Factory<Int, MovieItemEntity> = mMovieTVDao.getPopularMovies()

    fun getDetailMovie(id: Int): LiveData<MovieItemEntity> = mMovieTVDao.getDetailMovie(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieItemEntity> = mMovieTVDao.getFavoriteMovies()

    fun insertPopularMovie(listPopularMovies: List<MovieItemEntity>) = mMovieTVDao.insertPopularMovies(listPopularMovies)

    fun updateDetailMovie(detailMovie: MovieItemEntity) = mMovieTVDao.updateDetailMovie(detailMovie)

    fun setFavoriteMovie(itemMovie: MovieItemEntity, newState: Boolean){
        itemMovie.favorited = newState
        mMovieTVDao.updateDetailMovie(itemMovie)
    }
}