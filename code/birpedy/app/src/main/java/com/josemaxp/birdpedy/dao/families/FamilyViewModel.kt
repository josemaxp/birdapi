package com.josemaxp.birdpedy.dao.families

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FamilyViewModel  (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<Family>>
    private val repository: FamilyRepository

    init {
        val dao = AppDatabase.getInstance(application).familyDao()
        repository = FamilyRepository(dao)
        allValues = repository.allValues
    }

    fun basicData(): Family {
        return Family(-1, "", 1)
    }

    fun insert(value: Family) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(value)
        }
    }

    fun update(value: Family) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(value)
        }
    }

    fun getValue(id: Int): Family {
        return repository.getValue(id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}