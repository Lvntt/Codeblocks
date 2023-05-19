package com.example.codeblocks.domain.usecases

import com.example.codeblocks.domain.repository.ConsoleRepository

class ReadFromConsoleUseCase(private val consoleRepository: ConsoleRepository) {

    suspend operator fun invoke() = consoleRepository.readFromConsole()

}