package id.interconnect.moviesandtv.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.moviesandtv.databinding.FragmentTvHomeBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory
import id.interconnect.moviesandtv.vo.Status

class TVHomeFragment : Fragment(), OnClickItemCallback {
    private lateinit var fragmentTVHomeFragment: FragmentTvHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTVHomeFragment = FragmentTvHomeBinding.inflate(layoutInflater, container, false)
        return fragmentTVHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val actionbar = activity?.actionBar
//        actionbar?.title = "Popular TV Shows"

        if (activity != null) {
            val viewModel =
                ViewModelProvider(
                    this,
                    ViewModelFactory.getInstance(requireContext())
                )[TVViewModel::class.java]
            val myTVAdapter = TVListAdapter(this)
            viewModel.getPopularTV().observe(viewLifecycleOwner, { dataTVList ->
                if (dataTVList != null) {
                    when (dataTVList.status) {
                        Status.LOADING -> fragmentTVHomeFragment.tvProgressbar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTVHomeFragment.tvProgressbar.visibility = View.GONE
                            myTVAdapter.submitList(dataTVList.data)
                            myTVAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTVHomeFragment.tvProgressbar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTVHomeFragment.rvTvHome) {
                layoutManager = LinearLayoutManager(context)
                adapter = myTVAdapter
            }

        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity, TVDetailActivity::class.java)
        intent.putExtra(TVDetailActivity.KEY_TV_ID, id)
        startActivity(intent)
    }

}