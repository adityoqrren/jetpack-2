package id.interconnect.moviesandtv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.interconnect.moviesandtv.data.api.MovieTVRepository
import id.interconnect.moviesandtv.di.Injection
import id.interconnect.moviesandtv.ui.movies.MovieViewModel
import id.interconnect.moviesandtv.ui.tv.TVViewModel

class ViewModelFactory private constructor(private val mMovieTVRepository: MovieTVRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() : ViewModelFactory =
            instance?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MovieViewModel::class.java)->{
                return MovieViewModel(mMovieTVRepository) as T
            }
            modelClass.isAssignableFrom(TVViewModel::class.java)->{
                return TVViewModel(mMovieTVRepository) as T
            }
            else -> throw Throwable("Unknown Viewmodel")
        }
    }
}