package id.interconnect.moviesandtv.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.source.local.LocalDataSource
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.data.source.remote.response.ApiResponse
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource
import id.interconnect.moviesandtv.utils.AppExecutors
import id.interconnect.moviesandtv.utils.ListToString
import id.interconnect.moviesandtv.utils.SortUtils
import id.interconnect.moviesandtv.vo.Resource

class MovieTVRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTVDataSource {
    companion object {
        @Volatile
        private var instance: MovieTVRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieTVRepository =
            instance ?: synchronized(this) {
                instance ?: MovieTVRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply {
                    instance = this
                }
            }
    }

    override fun getPopularTV(): LiveData<Resource<PagedList<TVItemEntity>>> {
        return object : NetworkBoundResource<PagedList<TVItemEntity>, List<TVItem>>(appExecutors) {
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
                for (item in data) {
                    val tvItem = TVItemEntity(
                        id = item.id,
                        original_name = item.original_name,
                        poster_path = item.poster_path,
                        vote_average = item.vote_average,
                        first_air_date = item.first_air_date,
                        overview = item.overview
                    )
                    tvList.add(tvItem)
                }
                localDataSource.insertPopularTV(tvList)
            }

        }.asLiveData()
    }


    override fun getDetailTV(id: Int): LiveData<Resource<TVItemEntity>> {
        return object : NetworkBoundResource<TVItemEntity, DetailTV>(appExecutors) {
            override fun loadFromDB(): LiveData<TVItemEntity> =
                localDataSource.getDetailTV(id)


            override fun shouldFetch(data: TVItemEntity?): Boolean =
                data?.original_language == "" && data.popularity == 0.0 && data.number_of_episodes == 0


            override fun createCall(): LiveData<ApiResponse<DetailTV>> =
                remoteDataSource.getDetailTV(id)


            override fun saveCallResult(data: DetailTV) {
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
                    data.first_air_date,
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
        return LivePagedListBuilder(localDataSource.getFavoriteTV(), config).build()
    }

    override fun setFavoriteTv(tvItemEntity: TVItemEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTV(tvItemEntity, state) }
    }

    override fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieItemEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieItemEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieItemEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getPopularMovies(
                        SortUtils.getSortedQuery(
                            sort
                        )
                    ), config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieItemEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getPopularMovies()


            override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieItemEntity>()
                for (item in data) {
                    val movieItem = MovieItemEntity(
                        id = item.id,
                        original_title = item.title,
                        poster_path = item.poster_path,
                        vote_average = item.vote_average,
                        release_date = item.release_date,
                        overview = item.overview
                    )
                    movieList.add(movieItem)
                }
                localDataSource.insertPopularMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieItemEntity>> {
        return object : NetworkBoundResource<MovieItemEntity, DetailMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieItemEntity> =
                localDataSource.getDetailMovie(id)


            override fun shouldFetch(data: MovieItemEntity?): Boolean =
                data?.original_language == "" && data.popularity == 0.0


            override fun createCall(): LiveData<ApiResponse<DetailMovie>> =
                remoteDataSource.getDetailMovie(id)


            override fun saveCallResult(data: DetailMovie) {
                val movieItemEntity = MovieItemEntity(
                    data.id,
                    ListToString.GenresToString(data.genres),
                    data.original_language,
                    data.original_title,
                    data.overview,
                    data.popularity,
                    data.poster_path,
                    data.vote_average,
                    data.release_date,
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
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movieItemEntity: MovieItemEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieItemEntity, state) }
    }

}