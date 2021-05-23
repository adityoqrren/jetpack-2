package id.interconnect.moviesandtv.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.hasExtra("movie_id")){
            val id = intent.getIntExtra("movie_id",0)
            val movieViewModel = ViewModelProvider(this,ViewModelFactory.getInstance())[MovieViewModel::class.java]
            movieViewModel.getDetailMovie(id).observe(this, Observer {
                data ->
                    val companyList = ArrayList<String>()
                    for(company in data.production_companies){
                        companyList.add(company.name)
                    }
                    movie_detail_company.text = companyList.joinToString (
                        separator = ", "
                    )
                    val genreList = ArrayList<String>()
                    for(genre in data.genres){
                        genreList.add(genre.name)
                    }
                    movie_detail_genre.text = genreList.joinToString(separator = ", ")
                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(detail_movie_img)
                    movie_detail_title.text = data.original_title
                    if(data.adult){
                        movie_detail_adult.text = resources.getString(R.string.yes)
                    }else{
                        movie_detail_adult.text = resources.getString(R.string.no)
                    }
                    movie_detail_language.text = data.original_language
                    movie_detail_popularity.text = data.popularity.toString()
                    movie_detail_rating.text = data.vote_average.toString()
                    movie_detail_overview.text = data.overview
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}