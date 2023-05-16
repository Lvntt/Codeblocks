package com.example.codeblocks.domain.usecases

import com.example.codeblocks.domain.repository.ConsoleRepository

class ClearConsoleUseCase(private val consoleRepository: ConsoleRepository) {
    fun clear() = consoleRepository.clearConsole()
}