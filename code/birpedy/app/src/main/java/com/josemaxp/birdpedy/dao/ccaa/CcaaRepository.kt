package com.josemaxp.birdpedy.dao.ccaa

import kotlinx.coroutines.flow.Flow

class CcaaRepository (private val dao: CcaaDao){

    val allValues: Flow<List<Ccaa>> = dao.getAll()

    fun insert(value: Ccaa) = dao.insert(value)

    fun update(value: Ccaa) = dao.update(value)

    fun getValue(id: Int): Ccaa = dao.getValue(id)

    suspend fun deleteAll() = dao.deleteAll()

}