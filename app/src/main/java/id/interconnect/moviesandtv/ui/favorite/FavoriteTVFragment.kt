package id.interconnect.moviesandtv.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.databinding.FragmentFavoriteTvBinding
import id.interconnect.moviesandtv.ui.home.OnClickItemCallback
import id.interconnect.moviesandtv.ui.tv.TVDetailActivity
import id.interconnect.moviesandtv.ui.tv.TVListAdapter
import id.interconnect.moviesandtv.ui.tv.TVViewModel
import id.interconnect.moviesandtv.viewmodel.ViewModelFactory

class FavoriteTVFragment : Fragment(), OnClickItemCallback {
    private lateinit var favoriteTVFragment: FragmentFavoriteTvBinding
    private lateinit var viewModel: FavoriteTVViewModel
    private lateinit var myTVAdapter: TVListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteTVFragment = FragmentFavoriteTvBinding.inflate(layoutInflater,container,false)
        return favoriteTVFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favoriteTVFragment?.favtvRv)

        if(activity != null){

            viewModel = ViewModelProvider(this,
                ViewModelFactory.getInstance(requireContext()))[FavoriteTVViewModel::class.java]
            myTVAdapter = TVListAdapter(this)
            favoriteTVFragment.favtvProgressbar.visibility = View.VISIBLE
            viewModel.getFavoriteTV().observe(viewLifecycleOwner, { dataTVList ->
                favoriteTVFragment.favtvProgressbar.visibility = View.GONE
                myTVAdapter.submitList(dataTVList)
                myTVAdapter.notifyDataSetChanged()
                Log.d("data: ",myTVAdapter.currentList.toString())
            })

            favoriteTVFragment.favtvRv.layoutManager = LinearLayoutManager(context)
            favoriteTVFragment.favtvRv.adapter = myTVAdapter
            favoriteTVFragment.favtvRv.setHasFixedSize(true)

        }
    }

    override fun onitemClick(id: Int) {
        val intent = Intent(activity, TVDetailActivity::class.java)
        intent.putExtra(TVDetailActivity.KEY_TV_ID, id)
        startActivity(intent)
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback(){
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
            if(view != null){
                val swipePosition = viewHolder.adapterPosition
                val tvItem = myTVAdapter.getSwipedData(swipePosition)
                tvItem?.let { viewModel.setFavorite(it) }

                val mainView = favoriteTVFragment.root.rootView.findViewById<BottomNavigationView>(R.id.my_bottom_navigation)

                val snackbar = Snackbar.make(view as View, "Batalkan menghapus item sebelumnya?", Snackbar.LENGTH_LONG)
                snackbar.setAnchorView(mainView)
                snackbar.setAction("OK"){
                    v ->
                    tvItem?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }

    })

}