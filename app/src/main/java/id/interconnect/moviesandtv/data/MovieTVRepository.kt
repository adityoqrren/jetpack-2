package id.interconnect.moviesandtv.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.source.local.LocalDataSource
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.data.source.remote.response.ApiResponse
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource
import id.interconnect.moviesandtv.utils.AppExecutors
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.utils.ListToString
import id.interconnect.moviesandtv.vo.Resource

class MovieTVRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : MovieTVDataSource {
    companion object {
        @Volatile
        private var instance: MovieTVRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieTVRepository =
            instance ?: synchronized(this) {
                instance ?: MovieTVRepository(remoteDataSource, localDataSource, appExecutors).apply {
                    instance = this
                }
            }
    }

//    fun getPopularMovies(): LiveData<List<MovieItem>> {
//        val movieListLiveData = MutableLiveData<List<MovieItem>>()
//        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMovie {
//            override fun onAllMovieReceived(movieResponses: List<MovieItem>) {
//                movieListLiveData.postValue(movieResponses)
//            }
//        })
//        return movieListLiveData
//    }
//
//    fun getDetailMovie(id: Int): LiveData<DetailMovie> {
//        val movieDetailLiveData = MutableLiveData<DetailMovie>()
//        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovie {
//            override fun onDetailMovieReceived(detailMovieResponses: DetailMovie) {
//                movieDetailLiveData.postValue(detailMovieResponses)
//            }
//        })
//        return movieDetailLiveData
//    }

    override fun getPopularTV(): LiveData<Resource<PagedList<TVItemEntity>>> {
        return object : NetworkBoundResource<PagedList<TVItemEntity>, List<TVItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TVItemEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTV(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVItemEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TVItem>>> =
                remoteDataSource.getPopularTV()


            override fun saveCallResult(data: List<TVItem>) {
                val tvList = ArrayList<TVItemEntity>()
                for(item in data){
                    val tvItem = TVItemEntity(
                        id = item.id,
                        original_name = item.original_name,
                        poster_path = item.poster_path,
                        vote_average = item.vote_average,
                        overview = item.overview
                    )
                    tvList.add(tvItem)
                }
                localDataSource.insertPopularTV(tvList)
            }

        }.asLiveData()
    }

//    fun getPopularTV(): LiveData<List<TVItem>> {
//        val tvListLiveData = MutableLiveData<List<TVItem>>()
//        remoteDataSource.getPopularTV(object : RemoteDataSource.LoadPopularTV {
//            override fun onAllTVReceived(TVResponses: List<TVItem>) {
//                tvListLiveData.postValue(TVResponses)
//            }
//        })
//        return tvListLiveData
//    }

    override fun getDetailTV(id: Int): LiveData<Resource<TVItemEntity>> {
        return object: NetworkBoundResource<TVItemEntity, TVItem>(appExecutors){
            override fun loadFromDB(): LiveData<TVItemEntity> =
                localDataSource.getDetailTV(id)


            override fun shouldFetch(data: TVItemEntity?): Boolean =
                data?.original_language == "" && data.popularity == 0.0  && data.number_of_episodes == 0


            override fun createCall(): LiveData<ApiResponse<TVItem>> =
                remoteDataSource.getDetailTV(id)


            override fun saveCallResult(data: TVItem){
                val tvItemEntity = TVItemEntity(
                    data.id,
                    data.original_name,
                    data.poster_path,
                    ListToString.GenresToString(data.genres),
                    data.original_language,
                    data.popularity,
                    data.vote_average,
                    ListToString.createdByListToString(data.created_by),
                    data.number_of_episodes,
                    ListToString.ProductionCompaniesToString(data.production_companies),
                    data.overview
                )
                localDataSource.updateDetailTV(tvItemEntity)
            }

        }.asLiveData()
    }

    override fun getFavoriteTV(): LiveData<PagedList<TVItemEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTV(),config).build()
    }

    override fun setFavoriteTv(tvItemEntity: TVItemEntity, state: Boolean) {
        appExecutors.diskIO().execute{localDataSource.setFavoriteTV(tvItemEntity, state)}
    }

    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieItemEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieItemEntity>, List<MovieItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieItemEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieItemEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getPopularMovies()


            override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieItemEntity>()
                for(item in data){
                    val movieItem = MovieItemEntity(
                        id = item.id,
                        original_title = item.title,
                        poster_path = item.poster_path,
                        vote_average = item.vote_average,
                        overview = item.overview
                    )
                    movieList.add(movieItem)
                }
                localDataSource.insertPopularMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieItemEntity>> {
        return object: NetworkBoundResource<MovieItemEntity, DetailMovie>(appExecutors){
            override fun loadFromDB(): LiveData<MovieItemEntity> =
                localDataSource.getDetailMovie(id)


            override fun shouldFetch(data: MovieItemEntity?): Boolean =
                data?.original_language == "" && data.popularity == 0.0


            override fun createCall(): LiveData<ApiResponse<DetailMovie>> =
                remoteDataSource.getDetailMovie(id)


            override fun saveCallResult(data: DetailMovie){
                val movieItemEntity = MovieItemEntity(
                    data.id,
                    ListToString.GenresToString(data.genres),
                    data.original_language,
                    data.original_title,
                    data.overview,
                    data.popularity,
                    data.poster_path,
                    data.vote_average,
                    ListToString.ProductionCompaniesToString(data.production_companies),
                    data.adult,
                )
                localDataSource.updateDetailMovie(movieItemEntity)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieItemEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(),config).build()
    }

    override fun setFavoriteMovie(movieItemEntity: MovieItemEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieItemEntity, state) }
    }

//    fun getDetailTV(id: Int): LiveData<TVItem> {
//        val tvDetailLiveData = MutableLiveData<TVItem>()
//        remoteDataSource.getDetailTV(id, object : RemoteDataSource.LoadDetailTV {
//            override fun onDetailTVReceived(detailTVResponses: TVItem) {
//                tvDetailLiveData.postValue(detailTVResponses)
//            }
//        })
//        return tvDetailLiveData
//    }
}