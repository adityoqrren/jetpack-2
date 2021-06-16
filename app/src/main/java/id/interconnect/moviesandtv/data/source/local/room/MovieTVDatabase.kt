package id.interconnect.moviesandtv.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.interconnect.moviesandtv.data.source.local.entity.MovieItemEntity
import id.interconnect.moviesandtv.data.source.local.entity.TVItemEntity

@Database(
    entities = [TVItemEntity::class, MovieItemEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MovieTVDatabase : RoomDatabase() {
    abstract fun movieTVDao(): MovieTVDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTVDatabase? = null

        fun getInstance(context: Context): MovieTVDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieTVDatabase::class.java,
                    "MovieTV.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}