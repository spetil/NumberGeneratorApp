package com.example.numbergeneratorapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class NumberViewModel : ViewModel() {
    var uiState by mutableStateOf<NumberUiState>(NumberUiState.Idle)
        private set

    fun generateNumber() {
        viewModelScope.launch {
            uiState = NumberUiState.Loading
            delay(2000)

            // Simula uma chance de erro
            if (Random.nextBoolean()) {
                uiState = NumberUiState.Error("Erro ao gerar o número")
                return@launch
            }

            val randomNumber = Random.nextInt(1, 101)
            uiState = NumberUiState.Success(randomNumber)
        }
    }

    fun reset() {
        uiState = NumberUiState.Idle
    }
}
