package com.josemaxp.birdpedy.dao.birds

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aves")
data class Bird(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nombre_comun") val nombre_comun: String?,
    @ColumnInfo(name = "nombre_cientifico") val nombre_cientifico: String?,
    @ColumnInfo(name = "jpg") val jpg: String?,
    @ColumnInfo(name = "png") val png: String?,
    @ColumnInfo(name = "referencia") val referencia: String?,
    @ColumnInfo(name = "familia") val familia: Int?,
    @ColumnInfo(name = "amenaza") val amenaza: String?,
    @ColumnInfo(name = "longitud") val longitud: String?,
    @ColumnInfo(name = "envergadura") val envergadura: String?,
    @ColumnInfo(name = "identificacion") val identificacion: String?,
    @ColumnInfo(name = "info") val info: String?
)