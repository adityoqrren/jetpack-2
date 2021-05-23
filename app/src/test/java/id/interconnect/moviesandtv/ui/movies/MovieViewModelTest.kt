package id.interconnect.moviesandtv.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.interconnect.moviesandtv.data.MovieItem
import id.interconnect.moviesandtv.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val dummyDetailMovie = DummyData.generateDetailMovie()[0]
//    private val detailMovieId = dummyDetailMovie.id
//    private lateinit var apiService: ApiService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observer: Observer<List<MovieItem>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieTVRepository)
    }


    @Test
    fun getPopularMovie() {
        val response = DataDummy.generate
            .setBody(getJson("list_movie.json"))
        mockWebServer.enqueue(response)

        val movieList = MutableLiveData<List<MovieItem>>()
        movieList.value = DummyData.DummyListMovie()

        `when`(movieTVRepository.getPopularMovie()).thenReturn(movieList)
        verify<MovieTVRepository>(movieTVRepository).getPopularMovie()
        assertNotNull(movieList.value)

        viewModel.getPopularMovie().observeForever(observer)
        verify(observer).onChanged(DummyData.DummyListMovie())
    }

    fun getJson(path : String) : String {
        // Load the JSON response
        val uri = this.javaClass.classLoader!!.getResource(path)
        print(uri)
        val file = File(uri.path)
        return String(file.readBytes())
    }

//    @Test
//    fun getDetailMovie() {
//        val idFirstData = movieTVRepository.getPopularTV().value?.get(0)?.id
//        val movieDetail = viewModel.getDetailMovie(idFirstData as Int)
//        assertNotNull(movieDetail)
////        assertEquals(dummyDetailMovie.id,movieDetail.id)
////        assertEquals(dummyDetailMovie.poster_path,movieDetail.poster_path)
////        assertEquals(dummyDetailMovie.adult,movieDetail.adult)
////        assertEquals(dummyDetailMovie.genres,movieDetail.genres)
////        assertEquals(dummyDetailMovie.original_language,movieDetail.original_language)
////        assertEquals(dummyDetailMovie.original_title,movieDetail.original_title)
////        assertEquals(dummyDetailMovie.overview,movieDetail.overview)
////        assertEquals(dummyDetailMovie.popularity,movieDetail.popularity,0.001)
////        assertEquals(dummyDetailMovie.production_companies,movieDetail.production_companies)
////        assertEquals(dummyDetailMovie.vote_average,movieDetail.vote_average,0.001)
//    }
}