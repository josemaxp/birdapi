package com.josemaxp.birdpedy.dao.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User (
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    @ColumnInfo(name = "dni") val dni: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "codEmpresa") val codEmpresa: String,
    @ColumnInfo(name = "codVendedor") val codVendedor: String,
    @ColumnInfo(name = "nombreVendedor") val nombreVendedor: String
)