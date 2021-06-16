package id.interconnect.moviesandtv.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val HIGHEST_RATING = "Highest rating"
    const val LOWEST_RATING = "Lowest rating"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieitementities ")
        if (filter == HIGHEST_RATING) {
            simpleQuery.append("ORDER BY vote_average DESC")
        } else {
            simpleQuery.append("ORDER BY vote_average ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}