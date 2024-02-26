package com.josemaxp.birdpedy.dao.ccaa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CcaaViewModel  (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<Ccaa>>
    private val repository: CcaaRepository

    init {
        val dao = AppDatabase.getInstance(application).ccaaDao()
        repository = CcaaRepository(dao)
        allValues = repository.allValues
    }

    fun basicData(): Ccaa {
        return Ccaa(-1,  "")
    }

    fun insert(value: Ccaa){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(value)
        }
    }

    fun update(value: Ccaa){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(value)
        }
    }

    fun getValue(id: Int): Ccaa {
        return repository.getValue(id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

}