package id.interconnect.moviesandtv.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val moviesDummy = DummyData.generateDummyListMovies()
    private val movieId = moviesDummy[0].id
    private val detailMovieDummy = DummyData.generateDummyDetailMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerPopularMovies: Observer<Resource<PagedList<MovieItemEntity>>>

    @Mock
    private lateinit var observerDetailMovie: Observer<Resource<MovieItemEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieItemEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieTVRepository)
        viewModel.setSelectedMovieItem(movieId)
    }

    @Test
    fun getPopularMovies() {
        val dummyListMovies = Resource.success(pagedList)
        `when`(dummyListMovies.data?.size).thenReturn(moviesDummy.size)
        val movieList = MutableLiveData<Resource<PagedList<MovieItemEntity>>>()
        movieList.value = dummyListMovies

        `when`(movieTVRepository.getPopularMovies()).thenReturn(movieList)
        val movieEntities = viewModel.getPopularMovies().value?.data
        verify(movieTVRepository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observerPopularMovies)
        verify(observerPopularMovies).onChanged(dummyListMovies)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(detailMovieDummy)
        val detailMovie = MutableLiveData<Resource<MovieItemEntity>>()
        detailMovie.value = dummyDetailMovie

        `when`(movieTVRepository.getDetailMovie(movieId)).thenReturn(detailMovie)

        viewModel.detailMovie.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavorite() {
        val dummyDetailMovie = Resource.success(detailMovieDummy)
        val detailMovie = MutableLiveData<Resource<MovieItemEntity>>()
        detailMovie.value = dummyDetailMovie

        viewModel.detailMovie = detailMovie

        Mockito.doNothing().`when`(movieTVRepository).setFavoriteMovie(detailMovieDummy, true)

        viewModel.setFavorite()
        verify(movieTVRepository).setFavoriteMovie(detailMovieDummy, true)
        Mockito.verifyNoMoreInteractions(observerDetailMovie)
    }
}