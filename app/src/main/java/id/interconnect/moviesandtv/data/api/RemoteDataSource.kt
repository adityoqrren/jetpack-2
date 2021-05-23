package id.interconnect.moviesandtv.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.interconnect.moviesandtv.BuildConfig
import id.interconnect.moviesandtv.api.Apiclient
import id.interconnect.moviesandtv.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private val postApiInterface = Apiclient.getClient().create(ApiService::class.java)

    fun getPopularMovie():LiveData<List<MovieItem>>{
        val movieListLiveData = MutableLiveData<List<MovieItem>>()
        val call = postApiInterface.getPopularMovie(BuildConfig.MY_API_KEY)
        call.enqueue(object : Callback<AllMovieResponse>{
            override fun onFailure(call: Call<AllMovieResponse>, t: Throwable) {
                Log.d("disini errornya ","${t.message}")
            }

            override fun onResponse(
                call: Call<AllMovieResponse>,
                response: Response<AllMovieResponse>
            ) {
                if(response.isSuccessful){
                    movieListLiveData.postValue(response.body()?.results)
                }
            }

        })
        return movieListLiveData
    }


    fun getDetailMovie(id : Int): LiveData<DetailMovie> {
        val detailMovieLiveData = MutableLiveData<DetailMovie>()
        val call = postApiInterface.getDetailMovie(id,BuildConfig.MY_API_KEY)
        call.enqueue(object : Callback<DetailMovie>{
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Log.d("disini errornya ","${t.message}")
            }

            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if(response.isSuccessful){
                    detailMovieLiveData.postValue(response.body())
                }
            }

        })
        return detailMovieLiveData
    }

    fun getPopularTV() : LiveData<List<TVItem>>{
        val tvListLiveData = MutableLiveData<List<TVItem>>()
        val call = postApiInterface.getPopularTV(BuildConfig.MY_API_KEY)
        call.enqueue(object : Callback<AllTVResponse>{
            override fun onFailure(call: Call<AllTVResponse>, t: Throwable) {
                Log.d("disini errornya ","${t.message}")
            }

            override fun onResponse(
                call: Call<AllTVResponse>,
                response: Response<AllTVResponse>
            ) {
                if(response.isSuccessful){
                    tvListLiveData.postValue(response.body()?.results)
                }
            }

        })
        return tvListLiveData
    }

    fun getDetailTV(id:Int) : LiveData<DetailTV>{
        val detailTVLiveData = MutableLiveData<DetailTV>()
        val call = postApiInterface.getDetailTV(id,BuildConfig.MY_API_KEY)
        call.enqueue(object : Callback<DetailTV>{
            override fun onFailure(call: Call<DetailTV>, t: Throwable) {
                Log.d("disini errornya ","${t.message}")
            }

            override fun onResponse(call: Call<DetailTV>, response: Response<DetailTV>) {
                if(response.isSuccessful){
                    detailTVLiveData.postValue(response.body())
                }
            }

        })
        return detailTVLiveData
    }

}