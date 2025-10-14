package com.example.rockpaperscissors.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rockpaperscissors.model.Decision
import com.example.rockpaperscissors.viewmodel.GameViewModel

enum class GameScreen() {
    Game,
    GameResult
}

@Composable
fun GameApp(
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GameScreen.Game.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = GameScreen.Game.name) {
                GameScreen(viewModel) { navController.navigate(GameScreen.GameResult.name) }
            }
            composable(route = GameScreen.GameResult.name) {
                ResultScreen(
                    viewModel
                ) { navController.popBackStack(GameScreen.Game.name, inclusive = false) }
            }
        }
    }

}

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onClickDecision: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Button(onClick = { clickDecision(viewModel, onClickDecision, Decision.ROCK) }) {
                Text("ROCK")
            }
            Button(onClick = { clickDecision(viewModel, onClickDecision, Decision.PAPER) }) {
                Text("PAPER")
            }
            Button(onClick = { clickDecision(viewModel, onClickDecision, Decision.SCISSORS) }) {
                Text("SCISSORS")
            }
        }
    }
}

fun clickDecision(
    viewModel: GameViewModel,
    onClickDecision: () -> Unit,
    playerSelection: Decision
) {
    viewModel.play(playerSelection)
    onClickDecision()
}
