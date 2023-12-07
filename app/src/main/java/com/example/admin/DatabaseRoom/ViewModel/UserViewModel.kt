package com.example.admin.DatabaseRoom.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admin.DatabaseRoom.Dao.UserDao
import com.example.admin.DatabaseRoom.Entity.User
import kotlinx.coroutines.launch

// UserViewModel.kt
class UserViewModel(private val repository: UserDao) : ViewModel() {
    val allUsers: LiveData<List<User>> = repository.getAllUsers()

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }
}