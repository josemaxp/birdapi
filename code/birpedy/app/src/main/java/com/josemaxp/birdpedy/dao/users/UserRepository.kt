package com.josemaxp.birdpedy.dao.users

import kotlinx.coroutines.flow.Flow

class UserRepository (private val userDao: UserDao) {

    val allUser: Flow<List<User>> = userDao.getAll()

    fun getUser(userDni: String): User = userDao.getUser(userDni)

    fun getUserAndPassword(userDni: String, userPassword: String): User = userDao.getUserAndPassword(userDni, userPassword)

    fun update(value: User) = userDao.update(value)

    fun insert(value: User) = userDao.insert(value)

    suspend fun deleteAll() = userDao.deleteAll()
}