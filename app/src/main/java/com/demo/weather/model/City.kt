package com.demo.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = ["city_name"], unique = true)))
data class City(
    @ColumnInfo(name = "city_name") val name: String,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
