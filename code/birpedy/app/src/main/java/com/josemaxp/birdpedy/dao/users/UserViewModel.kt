package com.josemaxp.birdpedy.dao.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josemaxp.birdpedy.dao.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val allUser: Flow<List<User>>
    private val repository: UserRepository

    private var _userActive = MutableLiveData<User?>()
    val userActive: LiveData<User?> = _userActive

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUser = repository.allUser
        _userActive.value = null
    }

    fun setUserActive(user: User){
        _userActive.value = user
    }

    fun basicUser(): User {
        return User(dni = "", password = "", codEmpresa = "", codVendedor = "", nombreVendedor = "")
    }

    fun getUser(userDni: String): User {
        return repository.getUser(userDni)
    }

    fun getUserAndPassword(userDni: String, userPassword: String): User {
        return repository.getUserAndPassword(userDni, userPassword)
    }

    fun update(value: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(value)
        }
    }

    fun insert(value: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(value)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}