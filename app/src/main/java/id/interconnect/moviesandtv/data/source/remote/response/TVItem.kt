package id.interconnect.moviesandtv.data

data class TVItem (
    val id:Int,
    val original_name:String,
    val poster_path:String,
    val vote_average:Double,
    val first_air_date:String,
    val overview:String
)