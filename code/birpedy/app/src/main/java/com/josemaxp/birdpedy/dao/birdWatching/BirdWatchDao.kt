package com.josemaxp.birdpedy.dao.birdWatching

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BirdWatchDao {
    @Query("SELECT * FROM avistamientos")
    fun getAll(): Flow<List<BirdWatch>>

    @Query("SELECT * FROM avistamientos WHERE id = :id")
    fun getValue(id: Int): BirdWatch

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: BirdWatch)

    @Update
    fun update(value: BirdWatch)

    @Delete
    fun delete(value: BirdWatch)

    @Query("DELETE FROM avistamientos")
    suspend fun deleteAll()
}