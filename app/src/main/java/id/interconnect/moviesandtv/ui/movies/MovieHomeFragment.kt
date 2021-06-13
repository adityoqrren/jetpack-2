package id.interconnect.moviesandtv.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.FragmentMovieHomeBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import id.interconnect.moviesandtv.vo.Status


class MovieHomeFragment : Fragment(), OnClickItemCallback {
    private lateinit var fragmentMovieHomeBinding: FragmentMovieHomeBinding

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
            val viewModel =
                ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[MovieViewModel::class.java]
            val myMovieAdapter = MovieListAdapter(this)
            viewModel.getPopularMovies().observe(viewLifecycleOwner, { movieList ->
                if(movieList != null) {
                    when(movieList.status){
                        Status.LOADING ->  fragmentMovieHomeBinding.movieProgressbar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMovieHomeBinding.movieProgressbar.visibility = View.GONE
                            myMovieAdapter.submitList(movieList.data)
                            myMovieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR->{
                            fragmentMovieHomeBinding?.movieProgressbar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieHomeBinding.rvMovieHome) {
                layoutManager = LinearLayoutManager(context)
                adapter = myMovieAdapter
            }
        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MOVIE_ID, id)
        startActivity(intent)
    }

}