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
import androidx.navigation.NavHostController
import com.example.rockpaperscissors.viewmodel.GameViewModel

@Composable
fun ResultScreen(viewModel: GameViewModel, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                "You picked [decision]. \n" +
                        "Computer picked ${viewModel.gameUiState.collectAsState().value.computerDecision}"
            )
            Button(onClick = { navController.navigate(GameScreen.Game.name) }) {
                Text("Play Again")
            }
        }
    }
}
