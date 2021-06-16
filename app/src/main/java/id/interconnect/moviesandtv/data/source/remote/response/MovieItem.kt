package id.interconnect.moviesandtv.data

data class MovieItem(
    val id: Int,
    val title: String,
    val vote_average: Double,
    val release_date: String,
    val poster_path: String,
    val overview: String
)