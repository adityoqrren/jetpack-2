package id.interconnect.moviesandtv.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.interconnect.moviesandtv.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.homeViewPager.adapter = sectionPagerAdapter
        activityHomeBinding.homeTablayout.setupWithViewPager(activityHomeBinding.homeViewPager)

        supportActionBar?.elevation = 0f

    }
}