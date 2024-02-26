package com.josemaxp.birdpedy.dao.birdWatching

import kotlinx.coroutines.flow.Flow

class BirdWatchRepository (private val dao: BirdWatchDao){

    val allValues: Flow<List<BirdWatch>> = dao.getAll()

    fun insert(value: BirdWatch) = dao.insert(value)

    fun update(value: BirdWatch) = dao.update(value)

    fun getValue(id: Int): BirdWatch = dao.getValue(id)

    suspend fun delete(value: BirdWatch) = dao.delete(value)

    suspend fun deleteAll() = dao.deleteAll()

}