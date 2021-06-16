package id.interconnect.moviesandtv.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.*
import id.interconnect.moviesandtv.TestExecutor
import id.interconnect.moviesandtv.data.source.local.LocalDataSource
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource
import id.interconnect.moviesandtv.utils.AppExecutors
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.utils.LiveDataTestUtil
import id.interconnect.moviesandtv.utils.PagedListUtil
import id.interconnect.moviesandtv.vo.Resource
import junit.framework.TestCase.assertEquals


import junit.framework.TestCase.assertNotNull


import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTVRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)

    private val appExecutors = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    private val movieTvRepository = FakeMovieTVRepository(remote,local,appExecutors)

    private val movieResponses = DummyData.generateDummyListMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DummyData.generateDummyDetailMovie()
    private val tvResponses = DummyData.generateDummyListTV()
    private val tvId = tvResponses[0].id
    private val detailTVResponse = DummyData.generateDummyDetailTV()

//    @Test
//    fun getPopularMovies() {
//        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieItemEntity>
//        `when`(local.getPopularMovies()).thenReturn(dataSourceFactory)
//        movieTvRepository.getPopularMovies()
//
//        verify(local).getPopularMovies()
//        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyListMovies()))
//        assertNotNull(movieEntities.data)
//        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
//    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MovieItemEntity>()
        dummyDetailMovie.value = DummyData.generateDummyDetailMovie()
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyDetailMovie)


        val detailMovieEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailMovie(movieId))
        verify(local)
            .getDetailMovie(movieId)
        assertNotNull(detailMovieEntity)
        Assert.assertEquals(detailMovieResponse.id, detailMovieEntity.data?.id)
        Assert.assertEquals(detailMovieResponse.genres, detailMovieEntity.data?.genres)
        Assert.assertEquals(
            detailMovieResponse.original_language,
            detailMovieEntity.data?.original_language
        )
        Assert.assertEquals(detailMovieResponse.original_title, detailMovieEntity.data?.original_title)
        Assert.assertEquals(detailMovieResponse.overview, detailMovieEntity.data?.overview)
        Assert.assertEquals(detailMovieResponse.popularity, detailMovieEntity.data?.popularity as Double, 0.001)
        Assert.assertEquals(detailMovieResponse.poster_path, detailMovieEntity.data?.poster_path)
        Assert.assertEquals(detailMovieResponse.vote_average, detailMovieEntity.data?.vote_average as Double, 0.001)
        Assert.assertEquals(detailMovieResponse.release_date, detailMovieEntity.data?.release_date)
        Assert.assertEquals(
            detailMovieResponse.production_companies,
            detailMovieEntity.data?.production_companies
        )
        Assert.assertEquals(detailMovieResponse.adult, detailMovieEntity.data?.adult)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceRepository = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieItemEntity>
        `when` (local.getFavoriteMovies()).thenReturn(dataSourceRepository)
        movieTvRepository.getFavoriteMovies()

        verify(local).getFavoriteMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyListTV()))
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie(){
        val mockTVItem = mock(MovieItemEntity::class.java)
        val state = true

        doNothing().`when`(local).setFavoriteMovie(mockTVItem, state)
        movieTvRepository.setFavoriteMovie(mockTVItem,state)
        verify(local).setFavoriteMovie(mockTVItem,state)
    }

    @Test
    fun getPopularTV() {
        val dataSourceRepository = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVItemEntity>
        `when` (local.getPopularTV()).thenReturn(dataSourceRepository)
        movieTvRepository.getPopularTV()

        verify(local).getPopularTV()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyListTV()))
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTV() {
        val dummyDetailTV = MutableLiveData<TVItemEntity>()
        dummyDetailTV.value = DummyData.generateDummyDetailTV()
        `when`(local.getDetailTV(tvId)).thenReturn(dummyDetailTV)

        val detailTVEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailTV(tvId))
        verify(local)
            .getDetailTV(tvId)
        assertNotNull(detailTVEntity)
        Assert.assertEquals(detailTVResponse.id, detailTVEntity.data?.id)
        Assert.assertEquals(detailTVResponse.original_name, detailTVEntity.data?.original_name)
        Assert.assertEquals(detailTVResponse.poster_path, detailTVEntity.data?.poster_path)
        Assert.assertEquals(detailTVResponse.genres, detailTVEntity.data?.genres)
        Assert.assertEquals(detailTVResponse.original_language, detailTVEntity.data?.original_language)
        Assert.assertEquals(detailTVResponse.popularity, detailTVEntity.data?.popularity as Double, 0.001)
        Assert.assertEquals(detailTVResponse.vote_average, detailTVEntity.data?.vote_average as Double, 0.001)
        Assert.assertEquals(detailTVResponse.first_air_date, detailTVEntity.data?.first_air_date)
        Assert.assertEquals(detailTVResponse.created_by, detailTVEntity.data?.created_by)
        Assert.assertEquals(detailTVResponse.number_of_episodes, detailTVEntity.data?.number_of_episodes)
        Assert.assertEquals(
            detailTVResponse.production_companies,
            detailTVEntity.data?.production_companies
        )
        Assert.assertEquals(detailTVResponse.overview, detailTVEntity.data?.overview)
    }


    @Test
    fun getFavoriteTV() {
        val dataSourceRepository = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVItemEntity>
        `when` (local.getFavoriteTV()).thenReturn(dataSourceRepository)
        movieTvRepository.getFavoriteTV()

        verify(local).getFavoriteTV()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyListTV()))
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteTV(){
        val mockTVItem = mock(TVItemEntity::class.java)
        val state = true

        doNothing().`when`(local).setFavoriteTV(mockTVItem, state)
        movieTvRepository.setFavoriteTv(mockTVItem,state)
        verify(local).setFavoriteTV(mockTVItem,state)
    }

}