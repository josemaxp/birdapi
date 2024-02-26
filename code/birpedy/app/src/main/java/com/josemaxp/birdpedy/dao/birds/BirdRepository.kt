package com.josemaxp.birdpedy.dao.birds

import kotlinx.coroutines.flow.Flow

class BirdRepository (private val dao: BirdDao){

    val allValues: Flow<List<Bird>> = dao.getAll()

    fun insert(value: Bird) = dao.insert(value)

    fun update(value: Bird) = dao.update(value)

    fun getValue(id: Int): Bird = dao.getValue(id)

    suspend fun deleteAll() = dao.deleteAll()

}