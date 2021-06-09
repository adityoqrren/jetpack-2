package id.interconnect.moviesandtv.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.interconnect.moviesandtv.data.DetailTV
import id.interconnect.moviesandtv.data.TVItem
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
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerPopularTV: Observer<List<TVItem>>

    @Mock
    private lateinit var observerDetailTV: Observer<DetailTV>

    @Before
    fun setUp() {
        viewModel = TVViewModel(movieTVRepository)
    }

    @Test
    fun getPopularTVs() {
        val dummyListTV = DummyData.generateDummyListTV()
        val tvList = MutableLiveData<List<TVItem>>()
        tvList.value = dummyListTV
        `when`(movieTVRepository.getPopularTV()).thenReturn(tvList)
        val tvEntities = viewModel.getPopularTV().value
        verify(movieTVRepository).getPopularTV()
        assertNotNull(tvEntities)
        assertEquals(20, tvEntities?.size)

        viewModel.getPopularTV().observeForever(observerPopularTV)
        verify(observerPopularTV).onChanged(dummyListTV)
    }

    @Test
    fun getDetailTV() {
        val dummyDetailTV = DummyData.generateDummyDetailTV()
        val detailTV = MutableLiveData<DetailTV>()
        detailTV.value = dummyDetailTV
        `when`(movieTVRepository.getDetailTV(dummyDetailTV.id)).thenReturn(detailTV)
        val detailTVEntity = viewModel.getDetailTV(dummyDetailTV.id).value as DetailTV
        verify(movieTVRepository).getDetailTV(dummyDetailTV.id)
        assertNotNull(detailTVEntity)
        assertEquals(dummyDetailTV.id, detailTVEntity.id)
        assertEquals(dummyDetailTV.original_name, detailTVEntity.original_name)
        assertEquals(dummyDetailTV.poster_path, detailTVEntity.poster_path)
        assertEquals(dummyDetailTV.genres, detailTVEntity.genres)
        assertEquals(dummyDetailTV.original_language, detailTVEntity.original_language)
        assertEquals(dummyDetailTV.popularity, detailTVEntity.popularity, 0.001)
        assertEquals(dummyDetailTV.vote_average, detailTVEntity.vote_average, 0.001)
        assertEquals(dummyDetailTV.created_by, detailTVEntity.created_by)
        assertEquals(dummyDetailTV.number_of_episodes, detailTVEntity.number_of_episodes)
        assertEquals(dummyDetailTV.production_companies, detailTVEntity.production_companies)
        assertEquals(dummyDetailTV.overview, detailTVEntity.overview)

        viewModel.getDetailTV(dummyDetailTV.id).observeForever(observerDetailTV)
        verify(observerDetailTV).onChanged(dummyDetailTV)
    }

}