package com.example.diceroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlin.random.Random
import kotlin.random.nextInt

class DiceRollViewModel(
    userRepository: UserRepository = FakeUserRepository()
) : ViewModel() {
    private val rollState = MutableStateFlow(RollState())

    val uiState: StateFlow<DiceRollUiState> =
        combine(rollState, userRepository.userStream) { roll, user ->
            if (user.name.isEmpty()) {
                DiceRollUiState.LogUserIn
            } else {
                DiceRollUiState.DiceRoll(
                    user.name, user.numberOfRolls,
                    roll.firstDieValue, roll.secondDieValue
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DiceRollUiState.Loading
        )

    fun rollDice() {
        rollState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(1..6),
                secondDieValue = Random.nextInt(1..6),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }
}
