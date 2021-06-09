package id.interconnect.moviesandtv.di

import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource

object Injection {
    fun provideRepository() : MovieTVRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieTVRepository.getInstance(remoteDataSource)
    }
}