package com.josemaxp.birdpedy.dao.provinces

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.josemaxp.birdpedy.dao.ccaa.Ccaa

@Entity(tableName = "provincias", foreignKeys = [ForeignKey(entity = Ccaa::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("id_ccaa"))]
)
data class Province(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "id_ccaa") val id_ccaa: Int
)
