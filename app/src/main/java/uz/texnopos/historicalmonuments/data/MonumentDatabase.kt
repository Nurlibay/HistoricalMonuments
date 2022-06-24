package uz.texnopos.historicalmonuments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.historicalmonuments.data.dao.MonumentDao
import uz.texnopos.historicalmonuments.data.entity.Monument

@Database(entities = [Monument::class], version = 1)
abstract class MonumentDatabase : RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: MonumentDatabase
        fun getInstance(context: Context): MonumentDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context, MonumentDatabase::class.java,
                    "Monument.db"
                )
                    .createFromAsset("Monument.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): MonumentDao
}