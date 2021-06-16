package id.interconnect.moviesandtv.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.FragmentFavoriteMoviesBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.ui.movies.MovieDetailActivity
import id.interconnect.moviesandtv.ui.movies.MovieListAdapter
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory


class FavoriteMoviesFragment : Fragment(), OnClickItemCallback {
    private lateinit var favoriteMoviesFragment: FragmentFavoriteMoviesBinding
    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var myMovieAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        favoriteMoviesFragment =
            FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return favoriteMoviesFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(favoriteMoviesFragment.favmoviesRv)

        if (activity != null) {

            viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireContext())
            )[FavoriteMoviesViewModel::class.java]
            myMovieAdapter = MovieListAdapter(this)
            favoriteMoviesFragment.favmovieProgressbar.visibility = View.VISIBLE
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { dataTVList ->
                favoriteMoviesFragment.favmovieProgressbar.visibility = View.GONE
                myMovieAdapter.submitList(dataTVList)
                myMovieAdapter.notifyDataSetChanged()
                Log.d("data: ", myMovieAdapter.currentList.toString())
            })

            favoriteMoviesFragment.favmoviesRv.layoutManager = LinearLayoutManager(context)
            favoriteMoviesFragment.favmoviesRv.adapter = myMovieAdapter
            favoriteMoviesFragment.favmoviesRv.setHasFixedSize(true)

        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MOVIE_ID, id)
        startActivity(intent)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipePosition = viewHolder.adapterPosition
                val tvItem = myMovieAdapter.getSwipedData(swipePosition)
                tvItem?.let { viewModel.setFavorite(it) }

                val mainView =
                    favoriteMoviesFragment.root.rootView.findViewById<BottomNavigationView>(R.id.my_bottom_navigation)

                val snackbar = Snackbar.make(
                    view as View,
                    "Batalkan menghapus item sebelumnya?",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAnchorView(mainView)
                snackbar.setAction("OK") { v ->
                    tvItem?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }

    })
}