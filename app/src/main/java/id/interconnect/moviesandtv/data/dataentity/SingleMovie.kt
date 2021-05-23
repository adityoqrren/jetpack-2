package id.interconnect.moviesandtv.data.dataentity

data class SingleMovie (
    val id:Int,
    val genres:List<String>,
    val original_language:String,
    val original_title:String,
    val overview:String,
    val popularity:Double,
    val poster_path:String,
    val vote_average:Double,
    val production_companies:List<String>,
    val adult:Boolean
)