package id.interconnect.moviesandtv.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.interconnect.moviesandtv.data.DetailTV
import id.interconnect.moviesandtv.data.TVItem
import id.interconnect.moviesandtv.data.MovieTVRepository

class TVViewModel(private val repository: MovieTVRepository) : ViewModel() {

    fun getPopularTV(): LiveData<List<TVItem>> {
        return repository.getPopularTV()
    }

    fun getDetailTV(id: Int): LiveData<DetailTV> {
        return repository.getDetailTV(id)
    }
}