package id.interconnect.moviesandtv.data

data class DetailMovie(
    val id: Int,
    val genres: List<Genre>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val release_date: String,
    val production_companies: List<ProductionCompanies>,
    val adult: Boolean
)