package com.example.admin.DatabaseRoom.Repository

import androidx.lifecycle.LiveData
import com.example.admin.DatabaseRoom.Dao.UserDao
import com.example.admin.DatabaseRoom.Entity.User

// UserRepository.kt
class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}