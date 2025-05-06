package com.tecsup.proyecto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: UserDao = UserDatabase.getDatabase(application).userDao()

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    fun insert(user: User) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }
}

