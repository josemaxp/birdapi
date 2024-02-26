package com.josemaxp.birdpedy.dao.provinces

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProvinceViewModel  (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<Province>>
    private val repository: ProvinceRepository

    init {
        val dao = AppDatabase.getInstance(application).provinceDao()
        repository = ProvinceRepository(dao)
        allValues = repository.allValues
    }

    fun basicData(): Province {
        return Province(-1,  "", 1)
    }

    fun insert(value: Province){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(value)
        }
    }

    fun update(value: Province){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(value)
        }
    }

    fun getValue(id: Int): Province {
        return repository.getValue(id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}