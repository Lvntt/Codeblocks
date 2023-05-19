package com.example.codeblocks.presentation.viewmodel

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import com.example.codeblocks.domain.usecases.GetConsoleOutputUseCase
import com.example.codeblocks.domain.usecases.IsInputRequestedUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import kotlinx.coroutines.flow.Flow


class ConsoleViewModel(
    private val writeToConsoleUseCase: WriteToConsoleUseCase,
    getConsoleOutputUseCase: GetConsoleOutputUseCase,
    isInputRequestedUseCase: IsInputRequestedUseCase
) : ViewModel() {
    private val _consoleOutput = getConsoleOutputUseCase()
    val consoleOutput: Flow<AnnotatedString>
        get() = _consoleOutput

    private val _isInputRequested = isInputRequestedUseCase()
    val isInputRequested: Flow<Boolean>
        get() = _isInputRequested

    fun submitInput(message: String) =
        writeToConsoleUseCase.writeInputToConsole(message)
}