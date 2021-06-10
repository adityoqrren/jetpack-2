package id.interconnect.moviesandtv.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvitementities")
data class TVItemEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name="original_name")
    var original_name: String,

    @ColumnInfo(name="vote_average")
    var vote_average: String,

    @ColumnInfo(name="poster_path")
    var poster_path: String,

    @ColumnInfo(name="overview")
    var overview: String,

    @ColumnInfo(name="favorited")
    var favorited: Boolean = false

)