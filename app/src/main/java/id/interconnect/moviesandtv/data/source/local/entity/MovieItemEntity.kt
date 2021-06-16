package id.interconnect.moviesandtv.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieitementities")
data class MovieItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "genres")
    var genres: String = "",

    @ColumnInfo(name = "original_language")
    var original_language: String = "",

    @ColumnInfo(name = "original_title")
    val original_title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "production_companies")
    var production_companies: String = "",

    @ColumnInfo(name = "adult")
    var adult: Boolean = false,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)