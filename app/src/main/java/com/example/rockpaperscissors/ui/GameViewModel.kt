package com.example.rockpaperscissors.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rockpaperscissors.model.Decision
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _gameUiState =
        MutableStateFlow(
            GameUiState(
                computerDecision = Decision.NoDecision("game start"),
                playerDecision = Decision.NoDecision("game start"),
                playerWins = false
            )
        )
    val gameUiState: StateFlow<GameUiState> = _gameUiState

    fun updateComputerDecision() {
        val randomNumber = (1..3).random()
        val computerDecision = when(randomNumber) {
            1 -> Decision.Rock("rock")
            2 -> Decision.Paper("paper")
            3 -> Decision.Scissors("scissors")
            else -> Decision.NoDecision("error generating result")
        }

        viewModelScope.launch {
            _gameUiState.update { state ->
                state.copy(
                    computerDecision = computerDecision
                )
            }
        }
    }
}

data class GameUiState(
    val computerDecision: Decision,
    val playerDecision: Decision,
    val playerWins: Boolean
)