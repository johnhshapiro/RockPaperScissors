package com.example.rockpaperscissors.model

sealed class Decision {
    data class Rock(val message: String): Decision()
    data class Paper(val message: String): Decision()
    data class Scissors(val message: String): Decision()
    data class NoDecision(val message: String): Decision()
}
