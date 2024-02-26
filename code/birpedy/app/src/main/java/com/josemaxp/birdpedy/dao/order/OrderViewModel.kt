package com.josemaxp.birdpedy.dao.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OrderViewModel  (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<Order>>
    private val repository: OrderRepository

    init {
        val dao = AppDatabase.getInstance(application).orderDao()
        repository = OrderRepository(dao)
        allValues = repository.allValues
    }

    fun basicData(): Order {
        return Order(-1, "")
    }

    fun insert(value: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(value)
        }
    }

    fun update(value: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(value)
        }
    }

    fun getValue(id: Int): Order {
        return repository.getValue(id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}