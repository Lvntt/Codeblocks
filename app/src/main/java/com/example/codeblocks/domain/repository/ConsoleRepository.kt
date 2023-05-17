package com.example.codeblocks.domain.repository

import androidx.compose.ui.text.AnnotatedString
import com.example.codeblocks.domain.entity.MessageType
import kotlinx.coroutines.flow.Flow

abstract class ConsoleRepository {

    abstract val output: Flow<AnnotatedString>
    abstract val inputRequested: Flow<Boolean>
    abstract fun writeToConsole(message: String, messageType: MessageType)
    abstract suspend fun readFromConsole(): String
    abstract fun clearConsole()

}