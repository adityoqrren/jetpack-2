package id.interconnect.moviesandtv.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.interconnect.moviesandtv.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        home_view_pager.adapter = sectionPagerAdapter
        home_tablayout.setupWithViewPager(home_view_pager)

        supportActionBar?.elevation = 0f
    }
}