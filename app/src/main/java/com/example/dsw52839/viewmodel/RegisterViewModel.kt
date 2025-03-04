package com.example.dsw52839.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _confPassword = MutableStateFlow("")

    private val _isNameValid = MutableStateFlow(true)
    private val _isEmailValid = MutableStateFlow(true)
    private val _isPasswordValid = MutableStateFlow(true)
    private val _isConfPasswordValid = MutableStateFlow(true)

    val name: StateFlow<String> = _name
    val email: StateFlow<String> = _email
    val confPassword: StateFlow<String> = _confPassword
    val password: StateFlow<String> = _password

    val isNameValid: StateFlow<Boolean> = _isNameValid
    val isEmailValid: StateFlow<Boolean> = _isEmailValid
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid
    val isConfPasswordValid: StateFlow<Boolean> = _isConfPasswordValid

    fun onNameChange(newName: String) {
        _name.value = newName
        _isNameValid.value = newName.isNotEmpty()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = newEmail.length >= 3
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordValid.value = newPassword.length >= 6
    }

    fun onConfPasswordChange(newConfPassword: String) {
        _confPassword.value = newConfPassword
        _isConfPasswordValid.value = newConfPassword.length >= 6
    }
}