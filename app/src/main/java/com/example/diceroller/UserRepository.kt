package com.example.diceroller

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userStream: Flow<User>
}
