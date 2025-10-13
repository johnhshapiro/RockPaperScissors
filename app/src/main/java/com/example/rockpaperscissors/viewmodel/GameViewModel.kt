package com.example.rockpaperscissors.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rockpaperscissors.model.Decision
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    lateinit var playerDecision: String
    private val _gameUiState =
        MutableStateFlow(
            GameUiState(
                computerDecision = Decision.ROCK,
                playerDecision = Decision.ROCK,
                playerWins = false
            )
        )
    val gameUiState: StateFlow<GameUiState> = _gameUiState

    fun updateComputerDecision() {
        val computerDecision = Decision.entries.toTypedArray().random()

        _gameUiState.update { state ->
            state.copy(
                computerDecision = computerDecision
            )
        }
    }
}

data class GameUiState(
    val computerDecision: Decision,
    val playerDecision: Decision,
    val playerWins: Boolean
)