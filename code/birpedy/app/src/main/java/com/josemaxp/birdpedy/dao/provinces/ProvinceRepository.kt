package com.josemaxp.birdpedy.dao.provinces

import kotlinx.coroutines.flow.Flow

class ProvinceRepository (private val dao: ProvinceDao){

    val allValues: Flow<List<Province>> = dao.getAll()

    fun insert(value: Province) = dao.insert(value)

    fun update(value: Province) = dao.update(value)

    fun getValue(id: Int): Province = dao.getValue(id)

    suspend fun deleteAll() = dao.deleteAll()

}