package id.interconnect.moviesandtv.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.moviesandtv.databinding.FragmentTvHomeBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory

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

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel =
                ViewModelProvider(this, ViewModelFactory.getInstance())[TVViewModel::class.java]
            val myTVAdapter = TVListAdapter(this)
            fragmentTVHomeFragment.tvHomeProgressbar.visibility = View.VISIBLE
            viewModel.getPopularTV().observe(viewLifecycleOwner, { data ->
                fragmentTVHomeFragment.tvHomeProgressbar.visibility = View.GONE
                myTVAdapter.tvList = data
                myTVAdapter.notifyDataSetChanged()
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