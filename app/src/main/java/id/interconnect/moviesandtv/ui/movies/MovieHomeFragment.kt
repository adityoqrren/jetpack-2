package id.interconnect.moviesandtv.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_home.*

class MovieHomeFragment : Fragment(), OnClickItemCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val myMovieAdapter = MovieListAdapter(this)
        rv_movie_home.layoutManager = LinearLayoutManager(context)
        rv_movie_home.adapter = myMovieAdapter

        if(activity!=null){
            val viewModel = ViewModelProvider(this,ViewModelFactory.getInstance())[MovieViewModel::class.java]
            tv_movie_progressbar.visibility = View.VISIBLE
            viewModel.getPopularMovie().observe(viewLifecycleOwner, Observer {
                data ->
                Log.d("data di main",data.toString())
                tv_movie_progressbar.visibility = View.GONE
                myMovieAdapter.movieList = data
                myMovieAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity,MovieDetailActivity::class.java)
        intent.putExtra("movie_id",id)
        startActivity(intent)
    }

}