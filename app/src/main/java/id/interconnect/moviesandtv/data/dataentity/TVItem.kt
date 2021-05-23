package id.interconnect.moviesandtv.data.dataentity

data class TVItem (
    val id:Int,
    val original_name:String,
    val vote_average:Double,
    val poster_path:String,
    val overview:String
)