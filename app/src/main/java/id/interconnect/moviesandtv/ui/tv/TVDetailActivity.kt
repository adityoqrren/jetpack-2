package id.interconnect.moviesandtv.ui.tv

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.ActivityTvDetailBinding
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import id.interconnect.moviesandtv.vo.Status

class TVDetailActivity : AppCompatActivity() {
    private lateinit var tvDetailBinding: ActivityTvDetailBinding
    private lateinit var tvViewModel: TVViewModel
    private var menu: Menu? = null

    companion object {
        const val KEY_TV_ID = "tv_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this))[TVViewModel::class.java]

        tvDetailBinding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(tvDetailBinding.root)

        supportActionBar?.title = resources.getString(R.string.title_tv_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(KEY_TV_ID)) {
            val tvId = intent.getIntExtra(KEY_TV_ID, 0)
            if (tvId != 0) {
                Log.d("debug tvDetailActivity", "id: $tvId")
                tvViewModel.setSelectedTVItem(tvId)
                tvViewModel.detailTV.observe(this, { detailTV ->
                    if (detailTV != null) {
                        when (detailTV.status) {
                            Status.LOADING -> {
                                tvDetailBinding.detailTvProgressbar.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> if (detailTV.data != null) {
                                tvDetailBinding.detailTvProgressbar.visibility = View.GONE
                                val data = detailTV.data
                                Log.d("lihat nilai favorite", data.favorited.toString())
                                tvDetailBinding.tvDetailCompany.text = data.production_companies
                                tvDetailBinding.tvDetailCreatedby.text = data.created_by
                                tvDetailBinding.tvDetailGenre.text = data.genres
                                tvDetailBinding.tvDetailFirstAired.text = data.first_air_date
                                Glide.with(this)
                                    .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .into(tvDetailBinding.detailTvImg)
                                tvDetailBinding.tvDetailTitle.text = data.original_name
                                tvDetailBinding.tvDetailLanguage.text = data.original_language
                                tvDetailBinding.tvDetailPopularity.text = data.popularity.toString()
                                tvDetailBinding.tvDetailRating.text = data.vote_average.toString()
                                tvDetailBinding.tvDetailOverview.text = data.overview
                                tvDetailBinding.tvDetailEpisodes.text =
                                    data.number_of_episodes.toString()
                            }
                            Status.ERROR -> {
                                tvDetailBinding.detailTvProgressbar.visibility = View.GONE
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
        tvViewModel.detailTV.observe(this, { tvItemDetail ->
            if (tvItemDetail != null) {
                when (tvItemDetail.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> if (tvItemDetail.data != null) {
                        val state = tvItemDetail.data.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            Log.d("test click", "favorite clicked")
            tvViewModel.setFavorite()
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