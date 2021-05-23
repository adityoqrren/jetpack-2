package id.interconnect.moviesandtv.di

object Injection {
    fun provideRepository() : MovieTVRepository{
        return MovieTVRepository()
    }
}