package id.interconnect.moviesandtv.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.ActivityTvDetailBinding
import id.interconnect.moviesandtv.utils.ListToString
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory

class TVDetailActivity : AppCompatActivity() {
    private lateinit var tvDetailBinding: ActivityTvDetailBinding

    companion object {
        const val KEY_TV_ID = "tv_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvDetailBinding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(tvDetailBinding.root)

        supportActionBar?.title = resources.getString(R.string.title_tv_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(KEY_TV_ID)) {
            val tvViewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance()
            )[TVViewModel::class.java]
            val idDetailTV = intent.getIntExtra(KEY_TV_ID, 0)
            tvViewModel.getDetailTV(idDetailTV).observe(this, { data ->
                tvDetailBinding.tvDetailCompany.text =
                    ListToString.ProductionCompaniesToString(data.production_companies)
                tvDetailBinding.tvDetailCreatedby.text =
                    ListToString.createdByListToString(data.created_by)
                tvDetailBinding.tvDetailGenre.text = ListToString.GenresToString(data.genres)
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(tvDetailBinding.detailTvImg)
                tvDetailBinding.tvDetailTitle.text = data.original_name
                tvDetailBinding.tvDetailLanguage.text = data.original_language
                tvDetailBinding.tvDetailPopularity.text = data.popularity.toString()
                tvDetailBinding.tvDetailRating.text = data.vote_average.toString()
                tvDetailBinding.tvDetailOverview.text = data.overview
                tvDetailBinding.tvDetailEpisodes.text = data.number_of_episodes.toString()
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}