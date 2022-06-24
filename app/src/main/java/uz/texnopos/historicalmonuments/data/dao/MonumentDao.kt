package uz.texnopos.historicalmonuments.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.historicalmonuments.data.entity.Monument

@Dao
interface MonumentDao {

    @Query("SELECT * FROM monument")
    suspend fun getAllMonuments(): List<Monument>
}