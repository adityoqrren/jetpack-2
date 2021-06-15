package id.interconnect.moviesandtv.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import id.interconnect.moviesandtv.data.MovieTVRepository
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.ui.favorite.FavoriteMoviesViewModel
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
class FavoriteMoviesViewModelTest {
    private lateinit var viewModel: FavoriteMoviesViewModel
    private val moviesDummy = DummyData.generateDummyListMovies()
    private val detailMovieDummy = DummyData.generateDummyDetailMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTVRepository: MovieTVRepository

    @Mock
    private lateinit var observerFavoriteMovies: Observer<PagedList<MovieItemEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieItemEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(movieTVRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyListMovies = pagedList
        `when`(dummyListMovies.size).thenReturn(moviesDummy.size)
        val movieFavoriteList = MutableLiveData<PagedList<MovieItemEntity>>()
        movieFavoriteList.value = dummyListMovies

        `when`(movieTVRepository.getFavoriteMovies()).thenReturn(movieFavoriteList)
        val movieFavoriteEntities = viewModel.getFavoriteMovies()
        verify(movieTVRepository).getFavoriteMovies()
        assertNotNull(movieFavoriteEntities)
        assertEquals(20, movieFavoriteEntities.value?.size)

        viewModel.getFavoriteMovies().observeForever(observerFavoriteMovies)
        verify(observerFavoriteMovies).onChanged(dummyListMovies)
    }

    @Test
    fun setFavorite() {
        val MovieItemEntity = detailMovieDummy
        doNothing().`when`(movieTVRepository)
            .setFavoriteMovie(MovieItemEntity, !MovieItemEntity.favorited)
        viewModel.setFavorite(MovieItemEntity)
        verify(movieTVRepository).setFavoriteMovie(detailMovieDummy, !MovieItemEntity.favorited)
    }
}