package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.presentation.viewmodel.ConsoleViewModel
import com.example.codeblocks.ui.theme.ConsoleInput
import com.example.codeblocks.ui.theme.ConsoleOutput
import com.example.codeblocks.ui.theme.ConsolePadding
import com.example.codeblocks.ui.theme.ConsoleRegularTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConsoleScreen(
    modifier: Modifier = Modifier, viewModel: ConsoleViewModel = koinViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .padding(all = ConsolePadding), contentAlignment = Alignment.TopStart
    ) {
        val text by viewModel.consoleOutput.collectAsState(initial = buildAnnotatedString {})
        val inputRequested by viewModel.isInputRequested.collectAsState(initial = false)

        var input by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }

        Column {
            if (text.isNotEmpty()) {
                Text(
                    text = text, color = ConsoleOutput, style = ConsoleRegularTextStyle
                )
            }
            if (inputRequested) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.focusRequester(focusRequester),
                    textStyle = ConsoleRegularTextStyle.copy(color = ConsoleInput),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.submitInput(input)
                        input = DefaultValues.EMPTY_STRING
                    }),
                    singleLine = true
                )

                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }
    }
}