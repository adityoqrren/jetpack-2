package id.interconnect.moviesandtv.ui.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tv_detail.*

class TVDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_detail)

        supportActionBar?.title = "TV Show Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("tv_id")) {
            val tvViewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance()
            )[TVViewModel::class.java]
            val idDetailTV = intent.getIntExtra("tv_id", 0)
            tvViewModel.getDetailTV(idDetailTV).observe(this, Observer {
                data ->
                    val companyList = ArrayList<String>()
                    for(company in data.production_companies){
                        companyList.add(company.name)
                    }
                    tv_detail_company.text = companyList.joinToString(
                        separator = ", "
                    )
                    val createdByList = ArrayList<String>()
                    for(createdBy in data.created_by){
                        createdByList.add(createdBy.name)
                    }
                    tv_detail_createdby.text = createdByList.joinToString(
                        separator = ", "
                    )
                    val genreList = ArrayList<String>()
                    Log.d("data tv genre","${data.genres}")
                    for(genre in data.genres){
                        genreList.add(genre.name)
                    }
                    tv_detail_genre.text = genreList.joinToString(
                        separator = ", "
                    )
                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w185${data.poster_path}")
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(detail_tv_img)
                    tv_detail_title.text = data.original_name
                    tv_detail_language.text = data.original_language
                    tv_detail_popularity.text = data.popularity.toString()
                    tv_detail_rating.text = data.vote_average.toString()
                    tv_detail_overview.text = data.overview
                    tv_detail_episodes.text = data.number_of_episodes.toString()
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}