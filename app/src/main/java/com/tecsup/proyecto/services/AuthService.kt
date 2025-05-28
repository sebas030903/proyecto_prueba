package com.tecsup.proyecto.services
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await


class AuthService(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) {

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name).build()
        user?.updateProfile(profileUpdates)?.await()
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getUserName(): String? = auth.currentUser?.displayName
}