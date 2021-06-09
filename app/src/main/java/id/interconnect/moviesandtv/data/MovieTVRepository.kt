package id.interconnect.moviesandtv.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource

class MovieTVRepository private constructor(private val remoteDataSource: RemoteDataSource) {
    companion object {
        @Volatile
        private var instance: MovieTVRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieTVRepository =
            instance ?: synchronized(this) {
                instance ?: MovieTVRepository(remoteDataSource)
            }
    }

    fun getPopularMovies(): LiveData<List<MovieItem>> {
        val movieListLiveData = MutableLiveData<List<MovieItem>>()
        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMovie {
            override fun onAllMovieReceived(movieResponses: List<MovieItem>) {
                movieListLiveData.postValue(movieResponses)
            }
        })
        return movieListLiveData
    }

    fun getDetailMovie(id: Int): LiveData<DetailMovie> {
        val movieDetailLiveData = MutableLiveData<DetailMovie>()
        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovie {
            override fun onDetailMovieReceived(detailMovieResponses: DetailMovie) {
                movieDetailLiveData.postValue(detailMovieResponses)
            }
        })
        return movieDetailLiveData
    }

    fun getPopularTV(): LiveData<List<TVItem>> {
        val tvListLiveData = MutableLiveData<List<TVItem>>()
        remoteDataSource.getPopularTV(object : RemoteDataSource.LoadPopularTV {
            override fun onAllTVReceived(TVResponses: List<TVItem>) {
                tvListLiveData.postValue(TVResponses)
            }
        })
        return tvListLiveData
    }

    fun getDetailTV(id: Int): LiveData<DetailTV> {
        val tvDetailLiveData = MutableLiveData<DetailTV>()
        remoteDataSource.getDetailTV(id, object : RemoteDataSource.LoadDetailTV {
            override fun onDetailTVReceived(detailTVResponses: DetailTV) {
                tvDetailLiveData.postValue(detailTVResponses)
            }
        })
        return tvDetailLiveData
    }
}