package id.interconnect.moviesandtv.di

import android.content.Context
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.LocalDataSource
import id.interconnect.moviesandtv.data.source.local.room.MovieTVDatabase
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource
import id.interconnect.moviesandtv.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context) : MovieTVRepository {
        val database = MovieTVDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieTVDao())
        val appExecutors = AppExecutors()

        return MovieTVRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}