package com.josemaxp.birdpedy.dao.birdWatching

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BirdWatchViewModel (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<BirdWatch>>
    private val repository: BirdWatchRepository

    init {
        val dao = AppDatabase.getInstance(application).birdWatchDao()
        repository = BirdWatchRepository(dao)
        allValues = repository.allValues
    }

    fun basicData(): BirdWatch {
        return BirdWatch(-1,  -1)
    }

    fun insert(value: BirdWatch){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(value)
        }
    }

    fun update(value: BirdWatch){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(value)
        }
    }

    fun getClient(id: Int): BirdWatch {
        return repository.getValue(id)
    }

    fun delete(value: BirdWatch) {
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(value)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}