package id.interconnect.moviesandtv.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.interconnect.moviesandtv.databinding.FragmentFavoritesBinding
import id.interconnect.moviesandtv.ui.home.SectionPagerAdapter


class FavoritesFragment : Fragment() {
    private lateinit var fragmentFavorite: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavorite = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return fragmentFavorite.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(requireActivity(), childFragmentManager)
        fragmentFavorite.myViewPager.adapter = sectionPagerAdapter
        fragmentFavorite.homeTablayout.setupWithViewPager(fragmentFavorite.myViewPager)

    }

}