package id.interconnect.moviesandtv.utils

import id.interconnect.moviesandtv.data.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object DummyData {
    private fun parsingFileToString(filename: String): String? {
        var inputStream: InputStream? = null
        try {
            inputStream = javaClass.classLoader?.getResourceAsStream(filename)
            val stringBuilder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream as InputStream))
            var str: String? = reader.readLine()
            while (str != null) {
                stringBuilder.append(str)
                str = reader.readLine()
            }
            return stringBuilder.toString()
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        } finally {
            inputStream?.close()
        }
    }


    fun generateDummyListMovies(): List<MovieItem> {
        val listMovie = ArrayList<MovieItem>()
        try {
            val responseObject = JSONObject(parsingFileToString("list_movie.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val vote_average = movie.getDouble("vote_average")
                val poster_path = movie.getString("poster_path")
                val overview = movie.getString("overview")

                val movieItemResponse = MovieItem(id, title, vote_average, poster_path, overview)
                listMovie.add(movieItemResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listMovie
    }

    fun generateDummyListTV(): List<TVItem> {
        val listTV = ArrayList<TVItem>()
        try {
            val responseObject = JSONObject(parsingFileToString("list_tv.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)

                val id = tv.getInt("id")
                val original_name = tv.getString("original_name")
                val vote_average = tv.getDouble("vote_average")
                val poster_path = tv.getString("poster_path")
                val overview = tv.getString("overview")

                val tvItemResponse = TVItem(id=id, original_name=original_name, vote_average = vote_average, poster_path = poster_path, overview = overview)
                listTV.add(tvItemResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listTV
    }

    fun generateDummyDetailMovie(): DetailMovie {
        var detailMovie: DetailMovie? = null
        try {
            val responseObject = JSONObject(parsingFileToString("detail_movie_1.json").toString())
            val id = responseObject.getInt("id")
            val genres_array = responseObject.getJSONArray("genres")
            val genres = ArrayList<Genre>()
            for (i in 0 until genres_array.length()) {
                val data = genres_array[i] as JSONObject
                val thisId = data.getInt("id")
                val name = data.getString("name")
                val oneGenre = Genre(thisId, name)
                genres.add(oneGenre)
            }
            val orginal_language = responseObject.getString("original_language")
            val original_title = responseObject.getString("original_title")
            val overview = responseObject.getString("overview")
            val popularity = responseObject.getDouble("popularity")
            val poster_path = responseObject.getString("poster_path")
            val vote_average = responseObject.getDouble("vote_average")
            val production_companies_array = responseObject.getJSONArray("production_companies")
            val production_companies = ArrayList<ProductionCompanies>()
            for (i in 0 until production_companies_array.length()) {
                val data = production_companies_array[i] as JSONObject
                val name = data.getString("name")
                val origin_country = data.getString("origin_country")
                val one_company = ProductionCompanies(name, origin_country)
                production_companies.add(one_company)
            }
            val adult = responseObject.getBoolean("adult")
            detailMovie = DetailMovie(
                id,
                genres,
                orginal_language,
                original_title,
                overview,
                popularity,
                poster_path,
                vote_average,
                production_companies,
                adult
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return detailMovie as DetailMovie
    }

    fun generateDummyDetailTV(): TVItem {
        var detailTV: TVItem? = null
        try {
            val responseObject =
                JSONObject(parsingFileToString("detail_tv_1.json").toString())
            val thisId = responseObject.getInt("id")
            val original_name = responseObject.getString("original_name")
            val poster_path = responseObject.getString("poster_path")
            val genres_array = responseObject.getJSONArray("genres")
            val genres = ArrayList<Genre>()
            for (i in 0 until genres_array.length()) {
                val data = genres_array[i] as JSONObject
                val id = data.getInt("id")
                val name = data.getString("name")
                val one_genre = Genre(id, name)
                genres.add(one_genre)
            }
            val original_language = responseObject.getString("original_language")
            val popularity = responseObject.getDouble("popularity")
            val vote_average = responseObject.getDouble("vote_average")
            val created_by_array = responseObject.getJSONArray("created_by")
            val created_by = ArrayList<Producer>()
            for (i in 0 until created_by_array.length()) {
                val data = created_by_array[i] as JSONObject
                val id = data.getInt("id")
                val name = data.getString("name")
                val one_producer = Producer(id, name)
                created_by.add(one_producer)
            }
            val number_of_episodes = responseObject.getInt("number_of_episodes")
            val production_companies_array = responseObject.getJSONArray("production_companies")
            val production_companies = ArrayList<ProductionCompanies>()
            for (i in 0 until production_companies_array.length()) {
                val data = production_companies_array[i] as JSONObject
                val name = data.getString("name")
                val origin_country = data.getString("origin_country")
                val one_company = ProductionCompanies(name, origin_country)
                production_companies.add(one_company)
            }
            val overview = responseObject.getString("overview")
            detailTV = TVItem(
                thisId,
                original_name,
                poster_path,
                genres,
                original_language,
                popularity,
                vote_average,
                created_by,
                number_of_episodes,
                production_companies,
                overview
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return detailTV as TVItem
    }



}