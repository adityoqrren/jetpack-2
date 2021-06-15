package id.interconnect.moviesandtv.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity
import id.interconnect.moviesandtv.ui.favorite.FavoriteTVViewModel
import id.interconnect.moviesandtv.utils.DummyData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTVViewModelTest {
    private lateinit var viewModel: FavoriteTVViewModel
    private val tvDummy = DummyData.generateDummyListTV()
    private val detailTVDummy = DummyData.generateDummyDetailTV()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerFavoriteTV: Observer<PagedList<TVItemEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVItemEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTVViewModel(movieTVRepository)
    }

    @Test
    fun getFavoriteTV() {
        val dummyListTV = pagedList
        `when`(dummyListTV.size).thenReturn(tvDummy.size)
        val tvFavoriteList = MutableLiveData<PagedList<TVItemEntity>>()
        tvFavoriteList.value = dummyListTV

        `when`(movieTVRepository.getFavoriteTV()).thenReturn(tvFavoriteList)
        val tvFavoriteEntities = viewModel.getFavoriteTV()
        verify(movieTVRepository).getFavoriteTV()
        assertNotNull(tvFavoriteEntities)
        assertEquals(20, tvFavoriteEntities.value?.size)

        viewModel.getFavoriteTV().observeForever(observerFavoriteTV)
        verify(observerFavoriteTV).onChanged(dummyListTV)
    }

    @Test
    fun setFavorite() {
        val tvItemEntity = detailTVDummy
        doNothing().`when`(movieTVRepository).setFavoriteTv(tvItemEntity, !tvItemEntity.favorited)
        viewModel.setFavorite(tvItemEntity)
        verify(movieTVRepository).setFavoriteTv(detailTVDummy, !tvItemEntity.favorited)
    }
}