package com.josemaxp.birdpedy.dao.birds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BirdViewModel (application: Application): AndroidViewModel(application) {

    val allValues: Flow<List<Bird>>
    private val repository: BirdRepository

    private var _selectedBird = MutableLiveData<Bird>()
    val selectedBird: LiveData<Bird> = _selectedBird

    init {
        val dao = AppDatabase.getInstance(application).birdDao()
        repository = BirdRepository(dao)
        allValues = repository.allValues
        _selectedBird.value = basicData()
    }

    fun basicData(): Bird{
        return Bird(-1,  "", "", "", "", "", 1, "", "", "", "", "")
    }

    fun setSelectedBird(value: Bird){
        _selectedBird.value = value
    }

    fun insert(value: Bird){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(value)
        }
    }

    fun update(value: Bird){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(value)
        }
    }

    fun getValue(id: Int): Bird{
        return repository.getValue(id)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}