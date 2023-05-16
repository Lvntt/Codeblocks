package com.example.codeblocks.presentation.viewmodel

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.codeblocks.domain.usecases.GetConsoleOutputUseCase
import com.example.codeblocks.domain.usecases.IsInputRequestedUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase


class ConsoleViewModel(
    private val writeToConsoleUseCase: WriteToConsoleUseCase,
    getConsoleOutputUseCase: GetConsoleOutputUseCase,
    isInputRequestedUseCase: IsInputRequestedUseCase
) : ViewModel() {
    private val _consoleOutput = getConsoleOutputUseCase.getOutput().asLiveData()
    val consoleOutput: LiveData<AnnotatedString>
        get() = _consoleOutput

    private val _isInputRequested = isInputRequestedUseCase.isInputRequested().asLiveData()
    val isInputRequested: LiveData<Boolean>
        get() = _isInputRequested

    fun submitInput(message: String) =
        writeToConsoleUseCase.writeInputToConsole(message)
}