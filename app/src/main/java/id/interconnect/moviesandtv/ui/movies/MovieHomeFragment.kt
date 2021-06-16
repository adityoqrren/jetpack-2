package id.interconnect.moviesandtv.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.databinding.FragmentMovieHomeBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.utils.SortUtils
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import id.interconnect.moviesandtv.vo.Resource
import id.interconnect.moviesandtv.vo.Status


class MovieHomeFragment : Fragment(), OnClickItemCallback {
    private lateinit var fragmentMovieHomeBinding: FragmentMovieHomeBinding
    lateinit var viewModel: MovieViewModel
    private lateinit var myMovieAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieHomeBinding =
            FragmentMovieHomeBinding.inflate(layoutInflater, container, false)

        return fragmentMovieHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            if (activity is AppCompatActivity) {
                (activity as AppCompatActivity).setSupportActionBar(fragmentMovieHomeBinding.toolbar)
            }

            setHasOptionsMenu(true)

            myMovieAdapter = MovieListAdapter(this)
            viewModel = obtainViewModel()

            Log.d("check movie home fragment", "masuk onViewCreated")
            viewModel.getPopularMovies(SortUtils.HIGHEST_RATING)
                .observe(viewLifecycleOwner, movieObserver)

            with(fragmentMovieHomeBinding.rvMovieHome) {
                layoutManager = LinearLayoutManager(context)
                adapter = myMovieAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieItemEntity>>> { movieList ->
        if (movieList != null) {
            when (movieList.status) {
                Status.LOADING -> fragmentMovieHomeBinding.movieProgressbar.visibility =
                    View.VISIBLE
                Status.SUCCESS -> {
                    fragmentMovieHomeBinding.movieProgressbar.visibility = View.GONE
                    myMovieAdapter.submitList(movieList.data)
                    myMovieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    fragmentMovieHomeBinding.movieProgressbar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MOVIE_ID, id)
        startActivity(intent)
    }

    private fun obtainViewModel(): MovieViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sorting_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.menu_sort_highest_rating -> sort = SortUtils.HIGHEST_RATING
            R.id.menu_sort_lowest_rating -> sort = SortUtils.LOWEST_RATING
        }
        if (sort != "") {
            Log.d("check movie home fragment", "sort: $sort")
            viewModel.getPopularMovies(sort).observe(viewLifecycleOwner, movieObserver)
            item.setChecked(true)
        }
        return super.onOptionsItemSelected(item)
    }

}