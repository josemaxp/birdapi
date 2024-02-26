package com.josemaxp.birdpedy.dao.families

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FamilyDao {
    @Query("SELECT * FROM familias ORDER BY nombre")
    fun getAll(): Flow<List<Family>>

    @Query("SELECT * FROM familias WHERE id = :id")
    fun getValue(id: Int): Family

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: Family)

    @Update
    fun update(value: Family)

    @Delete
    fun delete(value: Family)

    @Query("DELETE FROM familias")
    suspend fun deleteAll()
}