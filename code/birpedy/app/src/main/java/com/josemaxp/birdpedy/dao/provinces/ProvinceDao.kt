package com.josemaxp.birdpedy.dao.provinces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProvinceDao {
    @Query("SELECT * FROM provincias ORDER BY nombre")
    fun getAll(): Flow<List<Province>>

    @Query("SELECT * FROM provincias WHERE id = :id")
    fun getValue(id: Int): Province

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: Province)

    @Update
    fun update(value: Province)

    @Delete
    fun delete(value: Province)

    @Query("DELETE FROM provincias")
    suspend fun deleteAll()
}