package id.interconnect.moviesandtv.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.di.Injection
import id.interconnect.moviesandtv.ui.favorite.FavoriteMoviesViewModel
import id.interconnect.moviesandtv.ui.favorite.FavoriteTVFragment
import id.interconnect.moviesandtv.ui.favorite.FavoriteTVViewModel
import id.interconnect.moviesandtv.ui.movies.MovieViewModel
import id.interconnect.moviesandtv.ui.tv.TVViewModel

class ViewModelFactory private constructor(private val mMovieTVRepository: MovieTVRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
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
            modelClass.isAssignableFrom(FavoriteTVViewModel::class.java)->{
                return FavoriteTVViewModel(mMovieTVRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java)->{
                return FavoriteMoviesViewModel(mMovieTVRepository) as T
            }
            else -> throw Throwable("Unknown Viewmodel")
        }
    }
}