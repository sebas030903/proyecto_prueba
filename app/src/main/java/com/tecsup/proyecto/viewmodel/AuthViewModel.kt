package com.tecsup.proyecto.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isAuthenticated = MutableStateFlow(firebaseAuth.currentUser != null)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    init {
        // Listener para cambios en el estado de autenticación
        firebaseAuth.addAuthStateListener {
            _isAuthenticated.value = it.currentUser != null
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isAuthenticated.value = task.isSuccessful && firebaseAuth.currentUser != null
                onResult(task.isSuccessful)
            }
    }

    fun logout() {
        firebaseAuth.signOut()
        _isAuthenticated.value = false
    }
}