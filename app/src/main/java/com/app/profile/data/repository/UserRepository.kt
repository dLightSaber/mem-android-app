package com.app.profile.data.repository

import com.app.profile.data.database.dao.UserDao
import com.app.profile.data.database.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    fun deleteUserFromDataBase(id: Int) {
        userDao.deleteUser(id)
    }

    fun insertOrUpdateUser(user: User) {
        userDao.insertOrUpdateUser(user)
    }

    fun deleteAll() {
        userDao.deleteAll()
    }

    fun getUserProfileData(email: String): User {
        return userDao.getProfileData(email)
    }

    suspend fun loginUser(email: String, password: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext userDao.loginUser(email, password)
    }

    suspend fun getExistingUser(email: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext userDao.checkForExistingUser(email)
    }
}