package id.interconnect.moviesandtv.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
//    tableName = "detailtventities",
//    primaryKeys= ["id"],
//    foreignKeys = [ForeignKey(entity = TVItem::class,
//    parentColumns = ["id"],
//    childColumns = ["id"])],
//    indices = [Index(value=["id"])]
)
data class DetailTVEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @NonNull
    @ColumnInfo(name = "original_name")
    var original_name: String,

    @NonNull
    @ColumnInfo(name = "vote_average")
    var vote_average: Double,

    @NonNull
    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @NonNull
    @ColumnInfo (name = "overview")
    var overview: String
)
