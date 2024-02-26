package com.josemaxp.birdpedy.dao.families

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.josemaxp.birdpedy.dao.order.Order

@Entity(tableName = "familias", foreignKeys = [ForeignKey(entity = Order::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("id_orden"))]
)
data class Family(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "id_orden") val id_orden: Int
)
