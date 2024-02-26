package com.josemaxp.birdpedy.dao.birds

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface BirdDao {
    @Query("SELECT * FROM aves ORDER BY nombre_comun")
    fun getAll(): Flow<List<Bird>>

    @Query("SELECT * FROM aves WHERE id = :id")
    fun getValue(id: Int): Bird

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: Bird)

    @Update
    fun update(value: Bird)

    @Delete
    fun delete(value: Bird)

    @Query("DELETE FROM aves")
    suspend fun deleteAll()
}