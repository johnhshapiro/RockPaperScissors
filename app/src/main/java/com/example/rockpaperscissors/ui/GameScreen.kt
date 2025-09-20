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

enum class GameScreen() {
    Game(),
    GameResult()
}

@Composable
fun GameApp(
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GameScreen.Game.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = GameScreen.Game.name) {
                GameScreen(viewModel, navController)
            }
            composable(route = GameScreen.GameResult.name) {
                ResultScreen(viewModel)
            }
        }
    }

}

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Button(onClick = { clickDecision(viewModel, navController) }) {
                Text("Rock")
            }
            Button(onClick = { clickDecision(viewModel, navController) }) {
                Text("Paper")
            }
            Button(onClick = { clickDecision(viewModel, navController) }) {
                Text("Scissors")
            }
        }
    }
}

fun clickDecision(viewModel: GameViewModel, navController: NavHostController) {
    viewModel.updateComputerDecision()
    navController.navigate(GameScreen.GameResult.name)
}

