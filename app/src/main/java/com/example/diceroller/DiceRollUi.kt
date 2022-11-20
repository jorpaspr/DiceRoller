package com.example.diceroller

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diceroller.DiceRollUiState.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DiceRollUi(viewModel: DiceRollViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DiceRollUi(uiState, viewModel::rollDice)
}

@Composable
fun DiceRollUi(uiState: DiceRollUiState, onRollDice: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is DiceRoll -> {
                Text(uiState.username)
                Spacer(modifier = Modifier.height(8.dp))
                Text(uiState.firstDieValue?.toString().orEmpty())
                Spacer(modifier = Modifier.height(8.dp))
                Text(uiState.secondDieValue?.toString().orEmpty())
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onRollDice) { Text("Roll dice") }
            }
            is Loading -> {
                CircularProgressIndicator()
            }
            is LogUserIn -> {
                Text("Please log in")
            }
        }
    }
}
