package id.interconnect.moviesandtv.ui.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.ActivityMovieDetailBinding
import id.interconnect.moviesandtv.utils.ListToString
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import id.interconnect.moviesandtv.vo.Status


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var movieDetailBinding: ActivityMovieDetailBinding
    private lateinit var movieViewModel: MovieViewModel
    private var menu: Menu? = null

    companion object {
        const val KEY_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)

        setContentView(movieDetailBinding.root)

        movieViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory.getInstance(this)
            )[MovieViewModel::class.java]

        supportActionBar?.title = resources.getString(R.string.title_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(KEY_MOVIE_ID)) {
            val movieId = intent.getIntExtra(KEY_MOVIE_ID, 0)
            if (movieId != 0) {
                movieViewModel.setSelectedMovieItem(movieId)
                movieViewModel.detailMovie.observe(this, { detailMovie ->
                    if (detailMovie != null) {
                        when (detailMovie.status) {
                            Status.LOADING -> {

                            }
                            Status.SUCCESS -> if(detailMovie.data != null){
                                val data = detailMovie.data

                                movieDetailBinding.movieDetailCompany.text =
                                    data.production_companies

                                movieDetailBinding.movieDetailGenre.text =
                                    data.genres
                                Glide.with(this)
                                    .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .into(movieDetailBinding.detailMovieImg)
                                movieDetailBinding.movieDetailTitle.text = data.original_title
                                if (data.adult) {
                                    movieDetailBinding.movieDetailAdult.text =
                                        resources.getString(R.string.yes)
                                } else {
                                    movieDetailBinding.movieDetailAdult.text =
                                        resources.getString(R.string.no)
                                }
                                movieDetailBinding.movieDetailLanguage.text = data.original_language
                                movieDetailBinding.movieDetailPopularity.text =
                                    data.popularity.toString()
                                movieDetailBinding.movieDetailRating.text =
                                    data.vote_average.toString()
                                movieDetailBinding.movieDetailOverview.text = data.overview

                            }
                            Status.ERROR -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                })
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_favorite, menu)
        this.menu = menu
        movieViewModel.detailMovie.observe(this,{movieItemDetail ->
            if(movieItemDetail != null){
                when(movieItemDetail.status){
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> if(movieItemDetail.data != null){
                        val state = movieItemDetail.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_favorite){
            movieViewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.menu_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}