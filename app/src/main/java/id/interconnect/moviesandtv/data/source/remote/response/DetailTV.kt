package id.interconnect.moviesandtv.data

data class DetailTV (
    val id:Int,
    val original_name:String,
    val poster_path:String,
    val genres: List<Genre>,
    val original_language:String,
    val popularity:Double,
    val vote_average:Double,
    val created_by : List<Producer>,
    val number_of_episodes: Int,
    val production_companies: List<ProductionCompanies>,
    val overview: String
)