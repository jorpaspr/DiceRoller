package com.example.diceroller

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FakeUserRepository : UserRepository {
    override val userStream: Flow<User>
        get() = flowOf(User("Player 1", 0)).onStart { delay(1000) }
}
