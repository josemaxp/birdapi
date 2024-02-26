package com.josemaxp.birdpedy.dao.birdWatching

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.josemaxp.birdpedy.dao.birds.Bird

@Entity(
    tableName = "avistamientos",
    foreignKeys = [ForeignKey(
        entity = Bird::class,
        parentColumns = ["id"],
        childColumns = ["aveId"]
    )]
)
data class BirdWatch(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "aveId") val birdId: Int
)