package com.example.diceroller

sealed interface DiceRollUiState {
    object Loading : DiceRollUiState

    data class DiceRoll(
        val username: String,
        val numberOfRolls: Int,
        val firstDieValue: Int? = null,
        val secondDieValue: Int? = null,
    ) : DiceRollUiState

    object LogUserIn : DiceRollUiState
}
