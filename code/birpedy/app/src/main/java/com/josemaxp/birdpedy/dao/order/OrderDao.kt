package com.josemaxp.birdpedy.dao.order

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM orden ORDER BY nombre")
    fun getAll(): Flow<List<Order>>

    @Query("SELECT * FROM orden WHERE id = :id")
    fun getValue(id: Int): Order

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: Order)

    @Update
    fun update(value: Order)

    @Delete
    fun delete(value: Order)

    @Query("DELETE FROM orden")
    suspend fun deleteAll()
}