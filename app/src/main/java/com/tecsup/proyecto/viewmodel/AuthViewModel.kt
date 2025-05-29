package com.tecsup.proyecto.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isAuthenticated = MutableStateFlow(firebaseAuth.currentUser != null)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {
            _isAuthenticated.value = it.currentUser != null
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onResult(false, "Correo no válido")
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isAuthenticated.value = task.isSuccessful && firebaseAuth.currentUser != null
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onResult(false, "Correo no válido")
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isAuthenticated.value = task.isSuccessful && firebaseAuth.currentUser != null
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun loginOrRegister(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        login(email, password) { success, error ->
            if (success) {
                onResult(true, null)
            } else {
                register(email, password) { regSuccess, regError ->
                    if (regSuccess) {
                        onResult(true, null)
                    } else {
                        onResult(false, regError ?: "Error desconocido al registrar.")
                    }
                }
            }
        }
    }

    fun loginWithCredential(credential: AuthCredential, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _isAuthenticated.value = task.isSuccessful && firebaseAuth.currentUser != null
                onResult(task.isSuccessful)
            }
    }

    fun logout() {
        firebaseAuth.signOut()
        _isAuthenticated.value = false
    }

    fun getUserName(): String? {
        return FirebaseAuth.getInstance().currentUser?.displayName
    }
}
