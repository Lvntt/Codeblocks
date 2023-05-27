package com.example.codeblocks.domain.usecases

import com.example.codeblocks.domain.entity.MessageType
import com.example.codeblocks.domain.repository.ConsoleRepository

class WriteToConsoleUseCase(private val consoleRepository: ConsoleRepository) {

    fun writeInputToConsole(message: String) =
        consoleRepository.writeToConsole(message, MessageType.INPUT)

    fun writeOutputToConsole(message: String) =
        consoleRepository.writeToConsole(message, MessageType.OUTPUT)

    fun writeErrorToConsole(message: String) =
        consoleRepository.writeToConsole(message, MessageType.ERROR)

}