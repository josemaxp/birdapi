package com.josemaxp.birdpedy.dao.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM usuarios")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM usuarios WHERE dni = :userDni")
    fun getUser(userDni: String): User

    @Query("SELECT * FROM usuarios WHERE dni = :userDni AND password = :userPassword")
    fun getUserAndPassword(userDni: String, userPassword: String): User

    @Update
    fun update(value: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(value: User)

    @Query("DELETE FROM usuarios")
    suspend fun deleteAll()
}