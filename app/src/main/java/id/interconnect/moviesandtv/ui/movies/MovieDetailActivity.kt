package id.interconnect.moviesandtv.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.ActivityMovieDetailBinding
import id.interconnect.moviesandtv.utils.ListToString
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var movieDetailBinding: ActivityMovieDetailBinding

    companion object {
        const val KEY_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)

        setContentView(movieDetailBinding.root)

        supportActionBar?.title = resources.getString(R.string.title_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(KEY_MOVIE_ID)) {
            val id = intent.getIntExtra(KEY_MOVIE_ID, 0)
            val movieViewModel =
                ViewModelProvider(this, ViewModelFactory.getInstance())[MovieViewModel::class.java]
            movieViewModel.getDetailMovie(id).observe(this, { data ->

                movieDetailBinding.movieDetailCompany.text =
                    ListToString.ProductionCompaniesToString(data.production_companies)

                movieDetailBinding.movieDetailGenre.text = ListToString.GenresToString(data.genres)
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieDetailBinding.detailMovieImg)
                movieDetailBinding.movieDetailTitle.text = data.original_title
                if (data.adult) {
                    movieDetailBinding.movieDetailAdult.text = resources.getString(R.string.yes)
                } else {
                    movieDetailBinding.movieDetailAdult.text = resources.getString(R.string.no)
                }
                movieDetailBinding.movieDetailLanguage.text = data.original_language
                movieDetailBinding.movieDetailPopularity.text = data.popularity.toString()
                movieDetailBinding.movieDetailRating.text = data.vote_average.toString()
                movieDetailBinding.movieDetailOverview.text = data.overview
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}