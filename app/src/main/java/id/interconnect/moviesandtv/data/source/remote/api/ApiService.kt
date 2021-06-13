package id.interconnect.moviesandtv.data.source.remote.api

import id.interconnect.moviesandtv.data.AllMoviesResponse
import id.interconnect.moviesandtv.data.AllTVResponse
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.TVItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key:String
    ) : Call<AllMoviesResponse>

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
    ) : Call<TVItem>
}