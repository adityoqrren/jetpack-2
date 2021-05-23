package id.interconnect.moviesandtv.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.interconnect.moviesandtv.data.MovieItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel
//    private val dummyDetailTV = DummyData.generateDetailTV()[0]
//    private val detailTVId = dummyDetailTV.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observer: Observer<List<MovieItem>>

    @Before
    fun setUp(){
        viewModel = TVViewModel(movieTVRepository)
    }

    @Test
    fun getPopularTV() {
        val tvList = viewModel.getPopularTV()
        verify<MovieTVRepository>(movieTVRepository).getPopularTV()
        assertNotNull(tvList)
    }
//
//    @Test
//    fun getDetailTV() {
//        val tvDetail = viewModel.getDetailTV(detailTVId)
//        assertNotNull(tvDetail)
//        assertEquals(dummyDetailTV.id,tvDetail.id)
//        assertEquals(dummyDetailTV.poster_path,tvDetail.poster_path)
//        assertEquals(dummyDetailTV.created_by,tvDetail.created_by)
//        assertEquals(dummyDetailTV.genres,tvDetail.genres)
//        assertEquals(dummyDetailTV.original_language,tvDetail.original_language)
//        assertEquals(dummyDetailTV.original_name,tvDetail.original_name)
//        assertEquals(dummyDetailTV.overview,tvDetail.overview)
//        assertEquals(dummyDetailTV.popularity,tvDetail.popularity,0.001)
//        assertEquals(dummyDetailTV.production_companies,tvDetail.production_companies)
//        assertEquals(dummyDetailTV.vote_average,tvDetail.vote_average,0.001)
//        assertEquals(dummyDetailTV.number_of_episodes,tvDetail.number_of_episodes)
//    }
    
}