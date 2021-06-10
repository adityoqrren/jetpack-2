package id.interconnect.moviesandtv.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.ui.favorite.FavoriteMoviesFragment
import id.interconnect.moviesandtv.ui.favorite.FavoriteTVsFragment
import id.interconnect.moviesandtv.ui.movies.MovieHomeFragment
import id.interconnect.moviesandtv.ui.tv.TVHomeFragment

class SectionPagerAdapter(private val mContext:Context, fm:FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_title_movie,R.string.tab_title_tv)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment =
        when(position){
            0->FavoriteMoviesFragment()
            1->FavoriteTVsFragment()
            else -> Fragment()
        }

    override fun getCount(): Int = TAB_TITLES.size
}