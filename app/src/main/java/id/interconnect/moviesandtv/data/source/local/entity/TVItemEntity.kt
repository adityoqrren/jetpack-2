package id.interconnect.moviesandtv.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvitementities")
data class TVItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "original_name")
    val original_name: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "genres")
    var genres: String = "",

    @ColumnInfo(name = "original_language")
    var original_language: String = "",

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "created_by")
    var created_by: String = "",

    @ColumnInfo(name = "number_of_episodes")
    var number_of_episodes: Int = 0,

    @ColumnInfo(name = "first_air_date")
    val first_air_date: String,

    @ColumnInfo(name = "production_companies")
    var production_companies: String = "",

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)