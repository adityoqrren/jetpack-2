package id.interconnect.moviesandtv.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.utils.DummyData
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{

    private val dummyListMovie = DummyData.generateDummyListMovie()
    private val dummyDetailMovie = DummyData.generateDetailMovie()[0]
    private val dummyListTV = DummyData.generateDummyListTV()
    private val dummyDetailTV = DummyData.generateDetailTV()[0]

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_home)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyListMovie.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie_home)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.movie_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_title)).check(matches(withText(dummyDetailMovie.original_title)))
        onView(withId(R.id.movie_detail_adult)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_adult)).check(matches(withText(if(dummyDetailMovie.adult) "Yes" else "No")))
        onView(withId(R.id.movie_detail_company)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_company)).check(matches(withText(dummyDetailMovie.production_companies.joinToString(separator = ", "))))
        onView(withId(R.id.movie_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_language)).check(matches(withText(dummyDetailMovie.original_language)))
        onView(withId(R.id.movie_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_genre)).check(matches(withText(dummyDetailMovie.genres.joinToString(separator = ", "))))
        onView(withId(R.id.movie_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_overview)).check(matches(withText(dummyDetailMovie.overview)))
        onView(withId(R.id.movie_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_popularity)).check(matches(withText(dummyDetailMovie.popularity.toString())))
        onView(withId(R.id.movie_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_rating)).check(matches(withText(dummyDetailMovie.vote_average.toString())))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTV(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_home)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyListTV.size))
    }

    @Test
    fun loadDetailTV(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_home)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyDetailTV.original_name)))
        onView(withId(R.id.tv_detail_company)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_company)).check(matches(withText(dummyDetailTV.production_companies.joinToString(separator = ", "))))
        onView(withId(R.id.tv_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_language)).check(matches(withText(dummyDetailTV.original_language)))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(dummyDetailTV.genres.joinToString(separator = ", "))))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(dummyDetailTV.overview)))
        onView(withId(R.id.tv_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_popularity)).check(matches(withText(dummyDetailTV.popularity.toString())))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(withText(dummyDetailTV.vote_average.toString())))
        onView(withId(R.id.tv_detail_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_episodes)).check(matches(withText(dummyDetailTV.number_of_episodes.toString())))
        onView(withId(R.id.tv_detail_createdby)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_createdby)).check(matches(withText(dummyDetailTV.created_by.joinToString(separator = ", "))))
        onView(withId(R.id.detail_tv_img)).check(matches(isDisplayed()))
    }
}