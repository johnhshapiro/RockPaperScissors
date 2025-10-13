package com.example.rockpaperscissors.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rockpaperscissors.viewmodel.GameViewModel

@Composable
fun ResultScreen(viewModel: GameViewModel, onClickPlayAgain: () -> Unit) {

    val playerSelection = viewModel.playerDecision
    val computerSelection: String? = viewModel.gameUiState.collectAsState().value.computerDecision.toString()

    val winningMoves = mapOf(
        "rock" to "scissors",
        "scissors" to "paper",
        "paper" to "rock"
    )

    val gameResult = when {
        playerSelection.equals(computerSelection, true) -> "DRAW"
        winningMoves[playerSelection.lowercase()] == computerSelection?.lowercase() -> "YOU WIN"
        else -> "YOU LOSE"
    }


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                "You picked $playerSelection. \n" +
                        "Computer picked $computerSelection \n" +
                        gameResult
            )
            Button(onClick = { onClickPlayAgain() }) {
                Text("Play Again")
            }
        }
    }
}
