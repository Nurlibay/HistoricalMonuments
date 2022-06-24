package uz.texnopos.historicalmonuments.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Monument(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "descripton")
    val description: String,
    @ColumnInfo(name = "picture")
    val picture: String,
)