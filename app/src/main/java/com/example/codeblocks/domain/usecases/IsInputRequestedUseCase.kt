package com.example.codeblocks.domain.usecases

import com.example.codeblocks.domain.repository.ConsoleRepository

class IsInputRequestedUseCase(private val consoleRepository: ConsoleRepository) {

    operator fun invoke() = consoleRepository.inputRequested

}