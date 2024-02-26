package com.josemaxp.birdpedy.dao.order

import kotlinx.coroutines.flow.Flow

class OrderRepository (private val dao: OrderDao){

    val allValues: Flow<List<Order>> = dao.getAll()

    fun insert(value: Order) = dao.insert(value)

    fun update(value: Order) = dao.update(value)

    fun getValue(id: Int): Order = dao.getValue(id)

    suspend fun deleteAll() = dao.deleteAll()

}