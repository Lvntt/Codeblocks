package com.example.codeblocks.domain.usecases

import com.example.codeblocks.domain.repository.ConsoleRepository

class ReadFromConsoleUseCase(private val consoleRepository: ConsoleRepository) {
    suspend fun readFromConsole() = consoleRepository.readFromConsole()
}