package id.interconnect.moviesandtv.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.interconnect.moviesandtv.R
import id.interconnect.moviesandtv.utils.DummyData
import id.interconnect.moviesandtv.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyListMovie = DummyData.generateDummyListMovies()
    private val dummyListTV = DummyData.generateDummyListTV()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_home)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyListMovie.size - 1
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie_home)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.movie_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_adult)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_company)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTV() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_home)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyListTV.size - 1
            )
        )
    }

    @Test
    fun loadDetailTV() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_home)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_company)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_createdby)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_img)).check(matches(isDisplayed()))
    }
}