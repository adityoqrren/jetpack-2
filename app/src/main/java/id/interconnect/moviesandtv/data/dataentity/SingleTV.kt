package id.interconnect.moviesandtv.data.dataentity

data class SingleTV (
    val id:Int,
    val original_name:String,
    val poster_path:String,
    val genres: List<String>,
    val original_language:String,
    val popularity:Double,
    val vote_average:Double,
    val created_by : List<String>,
    val number_of_episodes: Int,
    val production_companies: List<String>,
    val overview: String
)