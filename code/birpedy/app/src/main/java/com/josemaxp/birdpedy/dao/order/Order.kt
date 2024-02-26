package com.josemaxp.birdpedy.dao.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orden")
data class Order(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String
)
