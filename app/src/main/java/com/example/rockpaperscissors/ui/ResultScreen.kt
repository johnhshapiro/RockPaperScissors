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
import com.example.rockpaperscissors.model.Decision
import com.example.rockpaperscissors.viewmodel.GameViewModel

@Composable
fun ResultScreen(viewModel: GameViewModel, onClickPlayAgain: () -> Unit) {

    val gameUiState = viewModel.gameUiState.collectAsState().value

    val playerSelection = gameUiState.playerDecision
    val computerSelection = gameUiState.computerDecision

    val winningMoves = mapOf(
        Decision.ROCK to Decision.SCISSORS,
        Decision.SCISSORS to Decision.PAPER,
        Decision.PAPER to Decision.ROCK
    )

    val gameResult = when {
        playerSelection == computerSelection -> "DRAW"
        winningMoves[playerSelection] == computerSelection -> "YOU WIN"
        else -> "YOU LOSE"
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                "You picked $playerSelection\n" +
                        "Computer picked $computerSelection\n" +
                        gameResult
            )
            Button(onClick = { onClickPlayAgain() }) {
                Text("Play Again")
            }
        }
    }
}
