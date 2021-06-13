package id.interconnect.moviesandtv.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.interconnect.moviesandtv.data.*
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource

class FakeMovieTVRepository(val remoteDataSource: RemoteDataSource) {

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

    fun getDetailTV(id: Int): LiveData<TVItem> {
        val tvDetailLiveData = MutableLiveData<TVItem>()
        remoteDataSource.getDetailTV(id, object : RemoteDataSource.LoadDetailTV {
            override fun onDetailTVReceived(detailTVResponses: TVItem) {
                tvDetailLiveData.postValue(detailTVResponses)
            }
        })
        return tvDetailLiveData
    }
}