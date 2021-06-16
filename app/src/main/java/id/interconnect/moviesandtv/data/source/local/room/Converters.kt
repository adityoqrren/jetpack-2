package id.interconnect.moviesandtv.data.source.local.room

import androidx.room.TypeConverter
import id.interconnect.moviesandtv.data.Genre
import id.interconnect.moviesandtv.utils.ListToString

class Converters {
    @TypeConverter
    fun ListGenreToString(listGenre: List<Genre>): String {
        return ListToString.GenresToString(listGenre)
    }
}