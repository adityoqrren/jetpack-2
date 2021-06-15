package id.interconnect.moviesandtv.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.interconnect.moviesandtv.BuildConfig
import id.interconnect.moviesandtv.api.ApiClient
import id.interconnect.moviesandtv.data.*
import id.interconnect.moviesandtv.data.source.remote.api.ApiService
import id.interconnect.moviesandtv.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource().apply { instance = this }
                }
            }
    }

    private val postApiInterface = ApiClient.getClient().create(ApiService::class.java)

    fun getPopularMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        val call = postApiInterface.getPopularMovies(BuildConfig.MY_API_KEY)
        val resultPopularMovies = MutableLiveData<ApiResponse<List<MovieItem>>>()
        EspressoIdlingResource.increment()
        call.enqueue(object : Callback<AllMoviesResponse> {
            override fun onFailure(call: Call<AllMoviesResponse>, t: Throwable) {
                Log.d("disini errornya ", "${t.message}")
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<AllMoviesResponse>,
                response: Response<AllMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        resultPopularMovies.value = ApiResponse.success(it)
                    }
                } else {
                    response.errorBody()?.toString()?.let {
                        Log.d("ERROR RESPONSE", "onResponse: $it")
                    }
                }
                EspressoIdlingResource.decrement()
            }

        })

        return resultPopularMovies
    }


    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovie>> {
        val call = postApiInterface.getDetailMovie(id, BuildConfig.MY_API_KEY)
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovie>>()
        EspressoIdlingResource.increment()
        call.enqueue(object : Callback<DetailMovie> {
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Log.d("disini errornya ", "${t.message}")
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        resultDetailMovie.value = ApiResponse.success(it)
                    }
                } else {
                    response.errorBody()?.toString()?.let {
                        Log.d("ERROR RESPONSE", "onResponse: $it")
                    }
                }
                EspressoIdlingResource.decrement()
            }

        })
        return resultDetailMovie
    }

    fun getPopularTV(): LiveData<ApiResponse<List<TVItem>>> {
        val call = postApiInterface.getPopularTV(BuildConfig.MY_API_KEY)
        val resultPopularTV = MutableLiveData<ApiResponse<List<TVItem>>>()
        EspressoIdlingResource.increment()
        call.enqueue(object : Callback<AllTVResponse> {
            override fun onFailure(call: Call<AllTVResponse>, t: Throwable) {
                Log.d("disini errornya ", "${t.message}")
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<AllTVResponse>,
                response: Response<AllTVResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        resultPopularTV.value = ApiResponse.success(it)
                    }
                } else {
                    response.errorBody()?.toString()?.let {
                        Log.d("ERROR RESPONSE", "onResponse: $it")
                    }
                }
                EspressoIdlingResource.decrement()
            }

        })

        return resultPopularTV
    }

    fun getDetailTV(id: Int) : LiveData<ApiResponse<DetailTV>> {
        val call = postApiInterface.getDetailTV(id, BuildConfig.MY_API_KEY)
        val resultDetailTV = MutableLiveData<ApiResponse<DetailTV>>()
        EspressoIdlingResource.increment()
        call.enqueue(object : Callback<DetailTV> {
            override fun onFailure(call: Call<DetailTV>, t: Throwable) {
                Log.d("disini errornya ", "${t.message}")
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(call: Call<DetailTV>, response: Response<DetailTV>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        resultDetailTV.value = ApiResponse.success(it)
                    }
                } else {
                    response.errorBody()?.toString()?.let {
                        Log.d("ERROR RESPONSE", "onResponse: $it")
                    }
                }
                EspressoIdlingResource.decrement()
            }

        })
        return resultDetailTV
    }

//    interface LoadPopularMovie {
//        fun onAllMovieReceived(movieResponses: List<MovieItem>)
//    }
//
//    interface LoadDetailMovie {
//        fun onDetailMovieReceived(detailMovieResponses: DetailMovie)
//    }

//    interface LoadPopularTV {
//        fun onAllTVReceived(TVResponses: List<DetailTV>)
//    }
//
//    interface LoadDetailTV {
//        fun onDetailTVReceived(detailTVResponses: DetailTV)
//    }

}