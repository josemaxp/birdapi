package com.josemaxp.birdpedy.dao.families

import kotlinx.coroutines.flow.Flow

class FamilyRepository (private val dao: FamilyDao){

    val allValues: Flow<List<Family>> = dao.getAll()

    fun insert(value: Family) = dao.insert(value)

    fun update(value: Family) = dao.update(value)

    fun getValue(id: Int): Family = dao.getValue(id)

    suspend fun deleteAll() = dao.deleteAll()

}