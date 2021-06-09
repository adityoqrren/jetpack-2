package id.interconnect.moviesandtv.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.interconnect.moviesandtv.data.DetailMovie
import id.interconnect.moviesandtv.data.MovieItem
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerPopularMovies: Observer<List<MovieItem>>

    @Mock
    private lateinit var observerDetailMovies: Observer<DetailMovie>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieTVRepository)
    }


    @Test
    fun getPopularMovies() {
        val dummyListMovie = DummyData.generateDummyListMovies()
        val movieList = MutableLiveData<List<MovieItem>>()
        movieList.value = dummyListMovie

        `when`(movieTVRepository.getPopularMovies()).thenReturn(movieList)
        val movieEntities = viewModel.getPopularMovies().value
        verify(movieTVRepository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observerPopularMovies)
        verify(observerPopularMovies).onChanged(dummyListMovie)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = DummyData.generateDummyDetailMovie()
        val detailMovie = MutableLiveData<DetailMovie>()
        detailMovie.value = dummyDetailMovie
        `when`(movieTVRepository.getDetailMovie(dummyDetailMovie.id)).thenReturn(detailMovie)
        val detailMovieEntity = viewModel.getDetailMovie(dummyDetailMovie.id).value as DetailMovie
        verify(movieTVRepository).getDetailMovie(dummyDetailMovie.id)
        assertNotNull(detailMovieEntity)
        assertEquals(dummyDetailMovie.id, detailMovieEntity.id)
        assertEquals(dummyDetailMovie.genres, detailMovieEntity.genres)
        assertEquals(dummyDetailMovie.original_language, detailMovieEntity.original_language)
        assertEquals(dummyDetailMovie.original_title, detailMovieEntity.original_title)
        assertEquals(dummyDetailMovie.overview, detailMovieEntity.overview)
        assertEquals(dummyDetailMovie.popularity, detailMovieEntity.popularity, 0.001)
        assertEquals(dummyDetailMovie.poster_path, detailMovieEntity.poster_path)
        assertEquals(dummyDetailMovie.vote_average, detailMovieEntity.vote_average, 0.001)
        assertEquals(dummyDetailMovie.production_companies, detailMovieEntity.production_companies)
        assertEquals(dummyDetailMovie.adult, detailMovieEntity.adult)

        viewModel.getDetailMovie(dummyDetailMovie.id).observeForever(observerDetailMovies)
        verify(observerDetailMovies).onChanged(dummyDetailMovie)
    }
}