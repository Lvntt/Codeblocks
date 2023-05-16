package com.example.codeblocks.data.repository

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.codeblocks.domain.entity.MessageType
import com.example.codeblocks.domain.repository.ConsoleRepository
import com.example.codeblocks.ui.theme.ConsoleError
import com.example.codeblocks.ui.theme.ConsoleInput
import com.example.codeblocks.ui.theme.ConsoleOutput
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class ConsoleRepositoryImpl : ConsoleRepository() {

    private companion object {
        const val BUFFER_SIZE = 200
        fun getColorByType(messageType: MessageType): Color {
            return when (messageType) {
                MessageType.OUTPUT -> ConsoleOutput
                MessageType.INPUT -> ConsoleInput
                MessageType.ERROR -> ConsoleError
            }
        }
    }

    override val output = MutableSharedFlow<AnnotatedString>(1, 0, BufferOverflow.DROP_OLDEST)
    override val inputRequested = MutableStateFlow(false)
    private val outputBuffer = ArrayDeque<Pair<String, MessageType>>(BUFFER_SIZE)
    private val inputBuffer = MutableSharedFlow<String>(0, 1, BufferOverflow.DROP_OLDEST)

    override fun writeToConsole(message: String, messageType: MessageType) {
        if (outputBuffer.size == BUFFER_SIZE) {
            outputBuffer.removeFirst()
        }
        outputBuffer.add(Pair('\n' + message, messageType))
        inputBuffer.tryEmit(message)
        flush()
    }

    private fun flush() {
        val resultString = buildAnnotatedString {
            for (msg in outputBuffer) {
                withStyle(style = SpanStyle(color = getColorByType(messageType = msg.second))) {
                    append(msg.first)
                }
            }
        }
        output.tryEmit(resultString)
    }

    override suspend fun readFromConsole(): String {
        inputRequested.emit(true)
        val input = inputBuffer.first()
        inputRequested.emit(false)
        return input
    }

    override fun clearConsole() {
        outputBuffer.clear()
        flush()
    }

}