package id.interconnect.moviesandtv.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.interconnect.moviesandtv.BuildConfig
import id.interconnect.moviesandtv.data.MovieItem

class MovieTVRepository private constructor(private val remoteDataSource: RemoteDataSource) {
    companion object{
        @Volatile
        private var instance:RemoteDataSource? = null

        fun getInstance():RemoteDataSource =
            instance?: synchronized(this){
                instance?: synchronized(this){
                    instance?: RemoteDataSource()
                }
            }
    }
//    fun getPopularMovie(): LiveData<List<MovieItem>> {
//        val movieListLiveData = MutableLiveData<List<MovieItem>>()
//
//    }
}