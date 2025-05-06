package com.tecsup.proyecto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: UserDao = UserDatabase.getDatabase(application).userDao()
    val allUsers: StateFlow<List<User>> =
        userDao.getAllUsers().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveOrUpdateUser(user: User) {
        viewModelScope.launch {
            if (user.id == 0) {
                userDao.insert(user) // Nuevo usuario
            } else {
                userDao.update(user) // Actualizar
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.delete(user)
        }
    }
}

