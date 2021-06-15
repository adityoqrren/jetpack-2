package id.interconnect.moviesandtv.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel
    private val tvDummy = DummyData.generateDummyListTV()
    private val tvId = tvDummy[0].id
    private val detailTVDummy = DummyData.generateDummyDetailTV()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerPopularTV: Observer<Resource<PagedList<TVItemEntity>>>

    @Mock
    private lateinit var observerDetailTV: Observer<Resource<TVItemEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVItemEntity>

    @Before
    fun setUp() {
        viewModel = TVViewModel(movieTVRepository)
        viewModel.setSelectedTVItem(tvId)
    }

    @Test
    fun getPopularTVs() {
        val dummyListTV = Resource.success(pagedList)
        `when`(dummyListTV.data?.size).thenReturn(tvDummy.size)
        val tvList = MutableLiveData<Resource<PagedList<TVItemEntity>>>()
        tvList.value = dummyListTV

        `when`(movieTVRepository.getPopularTV()).thenReturn(tvList)
        val tvEntities = viewModel.getPopularTV().value?.data
        verify(movieTVRepository).getPopularTV()
        assertNotNull(tvEntities)
        assertEquals(20, tvEntities?.size)

        viewModel.getPopularTV().observeForever(observerPopularTV)
        verify(observerPopularTV).onChanged(dummyListTV)
    }

    @Test
    fun getDetailTV() {
        val dummyDetailTV = Resource.success(detailTVDummy)
        val detailTV = MutableLiveData<Resource<TVItemEntity>>()
        detailTV.value = dummyDetailTV

        `when`(movieTVRepository.getDetailTV(tvId)).thenReturn(detailTV)

        viewModel.detailTV.observeForever(observerDetailTV)
        verify(observerDetailTV).onChanged(dummyDetailTV)
    }

    @Test
    fun setFavorite() {
        val dummyDetailTV = Resource.success(detailTVDummy)
        val detailTV = MutableLiveData<Resource<TVItemEntity>>()
        detailTV.value = dummyDetailTV

        viewModel.detailTV = detailTV

        doNothing().`when`(movieTVRepository).setFavoriteTv(detailTVDummy, true)

        viewModel.setFavorite()
        verify(movieTVRepository).setFavoriteTv(detailTVDummy, true)
        verifyNoMoreInteractions(observerDetailTV)
    }

}