package id.interconnect.moviesandtv.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import id.interconnect.moviesandtv.data.source.remote.response.RemoteDataSource
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals


import junit.framework.TestCase.assertNotNull


import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieTVRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieTvRepository = FakeMovieTVRepository(remote)

    private val movieResponses = DummyData.generateDummyListMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DummyData.generateDummyDetailMovie()
    private val tvResponses = DummyData.generateDummyListTV()
    private val tvId = tvResponses[0].id
    private val detailTVResponse = DummyData.generateDummyDetailTV()

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularMovie)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getPopularMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieTvRepository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovie)
                .onDetailMovieReceived(detailMovieResponse)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())
        val detailMovieEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailMovie(movieId))
        verify(remote)
            .getDetailMovie(eq(movieId), any())
        assertNotNull(detailMovieEntity)
        Assert.assertEquals(detailMovieResponse.id, detailMovieEntity.id)
        Assert.assertEquals(detailMovieResponse.genres, detailMovieEntity.genres)
        Assert.assertEquals(
            detailMovieResponse.original_language,
            detailMovieEntity.original_language
        )
        Assert.assertEquals(detailMovieResponse.original_title, detailMovieEntity.original_title)
        Assert.assertEquals(detailMovieResponse.overview, detailMovieEntity.overview)
        Assert.assertEquals(detailMovieResponse.popularity, detailMovieEntity.popularity, 0.001)
        Assert.assertEquals(detailMovieResponse.poster_path, detailMovieEntity.poster_path)
        Assert.assertEquals(detailMovieResponse.vote_average, detailMovieEntity.vote_average, 0.001)
        Assert.assertEquals(
            detailMovieResponse.production_companies,
            detailMovieEntity.production_companies
        )
        Assert.assertEquals(detailMovieResponse.adult, detailMovieEntity.adult)
    }

    @Test
    fun getPopularTV() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularTV)
                .onAllTVReceived(tvResponses)
            null
        }.`when`(remote).getPopularTV(any())
        val tvEntities = LiveDataTestUtil.getValue(movieTvRepository.getPopularTV())
        verify(remote).getPopularTV(any())
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getDetailTV() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTV)
                .onDetailTVReceived(detailTVResponse)
            null
        }.`when`(remote).getDetailTV(eq(tvId), any())
        val detailTVEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailTV(tvId))
        verify(remote)
            .getDetailTV(eq(tvId), any())
        assertNotNull(detailTVEntity)
        Assert.assertEquals(detailTVResponse.id, detailTVEntity.id)
        Assert.assertEquals(detailTVResponse.original_name, detailTVEntity.original_name)
        Assert.assertEquals(detailTVResponse.poster_path, detailTVEntity.poster_path)
        Assert.assertEquals(detailTVResponse.genres, detailTVEntity.genres)
        Assert.assertEquals(detailTVResponse.original_language, detailTVEntity.original_language)
        Assert.assertEquals(detailTVResponse.popularity, detailTVEntity.popularity, 0.001)
        Assert.assertEquals(detailTVResponse.vote_average, detailTVEntity.vote_average, 0.001)
        Assert.assertEquals(detailTVResponse.created_by, detailTVEntity.created_by)
        Assert.assertEquals(detailTVResponse.number_of_episodes, detailTVEntity.number_of_episodes)
        Assert.assertEquals(
            detailTVResponse.production_companies,
            detailTVEntity.production_companies
        )
        Assert.assertEquals(detailTVResponse.overview, detailTVEntity.overview)
    }


}