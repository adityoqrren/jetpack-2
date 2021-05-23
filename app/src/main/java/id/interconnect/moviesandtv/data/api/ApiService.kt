package id.interconnect.moviesandtv.data.api

import id.interconnect.moviesandtv.data.AllMovieResponse
import id.interconnect.moviesandtv.data.AllTVResponse
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.DetailTV
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key:String
    ) : Call<AllMovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id:Int,
        @Query("api_key") api_key:String
    ) : Call<DetailMovie>

    @GET("tv/popular")
    fun getPopularTV (
        @Query("api_key") api_key:String
    ) : Call<AllTVResponse>

    @GET("tv/{id}")
    fun getDetailTV(
        @Path("id") id:Int,
        @Query("api_key") api_key:String
    ) : Call<DetailTV>
}