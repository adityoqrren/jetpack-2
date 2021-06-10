package id.interconnect.moviesandtv.data

data class DetailTV (
    val id:Int,
    val original_name:String,
    val poster_path:String,
    var genres: List<Genre> = emptyList(),
    val original_language:String = "",
    var popularity:Double = 0.0,
    val vote_average:Double,
    var created_by : List<Producer> = emptyList(),
    var number_of_episodes: Int = 0,
    var production_companies: List<ProductionCompanies> = emptyList(),
    val overview: String
)