package com.softwama.now.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softwama.now.features.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> {
                _state.update { it.copy(mail = event.mail) }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is LoginEvent.Login -> {
                login()
            }
        }
    }

    private fun login() {
        val email = _state.value.mail
        val password = _state.value.password

        // Validación básica de email
        if (email.isBlank()) {
            _state.update { it.copy(error = "El correo es obligatorio") }
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update { it.copy(error = "Correo inválido") }
            return
        }

        // Validación de contraseña vacía (opcional)
        if (password.isBlank()) {
            _state.update { it.copy(error = "La contraseña es obligatoria") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            loginUseCase(email, password)
                .catch { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "Error al iniciar sesión"
                        )
                    }
                }
                .collect { result ->
                    result.onSuccess { response ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true,
                                loginMessage = response.message
                            )
                        }
                    }.onFailure { e ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = e.message ?: "Error desconocido"
                            )
                        }
                    }
                }
        }
    }

}

sealed class LoginEvent {
    data class UsernameChanged(val mail: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Login : LoginEvent()
}

data class LoginState(
    val mail: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false,
    val loginMessage: String? = null
)
