package id.interconnect.moviesandtv.data.dataentity

data class MovieItem (
    val id:Int,
    val title:String,
    val vote_average:Double,
    val poster_path:String,
    val overview:String
)