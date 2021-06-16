package id.interconnect.moviesandtv.utils

object DateUtil {
    fun dateStringGetYear(date: String): String {
        val date_to_arr = date.split("-")
        val year = date_to_arr[0]
        return year
    }
}