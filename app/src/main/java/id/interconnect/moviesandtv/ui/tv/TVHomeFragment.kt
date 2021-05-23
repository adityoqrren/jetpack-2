package id.interconnect.moviesandtv.ui.tv

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_tv_home.*

class TVHomeFragment : Fragment(), OnClickItemCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val myTVAdapter = TVListAdapter(this)
        rv_tv_home.layoutManager = LinearLayoutManager(context)
        rv_tv_home.adapter = myTVAdapter

        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[TVViewModel::class.java]

        tv_home_progressbar.visibility = View.VISIBLE
        if (activity != null) {
            viewModel.getPopularTV().observe(viewLifecycleOwner, Observer {
                data ->
                tv_home_progressbar.visibility = View.GONE
                myTVAdapter.tvList = data
                myTVAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onitemClick(id: Int) {
//        Toast.makeText(activity,"This is id $id",Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, TVDetailActivity::class.java)
        intent.putExtra("tv_id", id)
        startActivity(intent)
    }

}