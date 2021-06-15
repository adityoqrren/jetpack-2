package id.interconnect.moviesandtv.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity

@Dao
interface MovieTVDao {

    //tv

    @Query("SELECT * FROM tvitementities")
    fun getPopularTV(): DataSource.Factory<Int, TVItemEntity>

    @Transaction
    @Query("SELECT * FROM tvitementities WHERE id = :id")
    fun getDetailTV(id: Int) : LiveData<TVItemEntity>

    @Query("SELECT * FROM tvitementities WHERE favorited = 1")
    fun getFavoriteTV(): DataSource.Factory<Int, TVItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTV(listPopularTV: List<TVItemEntity>)

    @Update
    fun updateDetailTV(detailTV: TVItemEntity)

    //movie

    @Query("SELECT * FROM movieitementities")
    fun getPopularMovies(): DataSource.Factory<Int, MovieItemEntity>

    @Transaction
    @Query("SELECT * FROM movieitementities WHERE id = :id")
    fun getDetailMovie(id: Int) : LiveData<MovieItemEntity>

    @Query("SELECT * FROM movieitementities WHERE favorited = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(listPopularMovies: List<MovieItemEntity>)

    @Update
    fun updateDetailMovie(detailMovie: MovieItemEntity)

}