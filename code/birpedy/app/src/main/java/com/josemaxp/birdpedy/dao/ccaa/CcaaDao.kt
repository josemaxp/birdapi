package com.josemaxp.birdpedy.dao.ccaa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CcaaDao {
    @Query("SELECT * FROM ccaa ORDER BY nombre")
    fun getAll(): Flow<List<Ccaa>>

    @Query("SELECT * FROM ccaa WHERE id = :id")
    fun getValue(id: Int): Ccaa

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: Ccaa)

    @Update
    fun update(value: Ccaa)

    @Delete
    fun delete(value: Ccaa)

    @Query("DELETE FROM ccaa")
    suspend fun deleteAll()
}