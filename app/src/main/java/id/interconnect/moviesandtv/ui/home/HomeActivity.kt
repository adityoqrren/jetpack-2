package id.interconnect.moviesandtv.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.ActivityHomeBinding
import id.interconnect.moviesandtv.ui.favorite.FavoritesFragment
import id.interconnect.moviesandtv.ui.movies.MovieHomeFragment
import id.interconnect.moviesandtv.ui.tv.TVHomeFragment


class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding: ActivityHomeBinding

    private val navBottom = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.movie_menu -> {
                val fragment_choosen = MovieHomeFragment()
                addFragment(fragment_choosen)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tvshows_menu -> {
                val fragment_choosen = TVHomeFragment()
                addFragment(fragment_choosen)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite_menu -> {
                val fragment_choosen = FavoritesFragment()
                addFragment(fragment_choosen)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment_choosen: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.my_container, fragment_choosen)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        activityHomeBinding.myBottomNavigation.setOnNavigationItemSelectedListener(navBottom)
        supportActionBar?.elevation = 0f
        if (savedInstanceState == null) {
            addFragment(MovieHomeFragment())
        }
    }
}