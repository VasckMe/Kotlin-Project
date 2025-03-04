package com.example.dsw52839.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isEmailValid = MutableStateFlow(true)
    private val _isPasswordValid = MutableStateFlow(true)

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val isEmailValid: StateFlow<Boolean> = _isEmailValid
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = newEmail.length >= 3
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordValid.value = newPassword.length >= 6
    }
}