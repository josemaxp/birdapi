package com.josemaxp.birdpedy.dao.ccaa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ccaa")
data class Ccaa (
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String
)