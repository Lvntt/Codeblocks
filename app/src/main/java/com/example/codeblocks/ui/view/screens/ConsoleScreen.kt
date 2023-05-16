package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import com.example.codeblocks.presentation.viewmodel.ConsoleViewModel
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.ConsoleBackground
import com.example.codeblocks.ui.theme.ConsoleInput
import com.example.codeblocks.ui.theme.ConsoleOutput
import com.example.codeblocks.ui.theme.ConsoleRegularTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConsoleScreen(
    modifier: Modifier = Modifier,
    viewModel: ConsoleViewModel = koinViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .background(ConsoleBackground),
        contentAlignment = Alignment.TopStart
    ) {
        val text by viewModel.consoleOutput.observeAsState(initial = buildAnnotatedString {})
        val inputRequested by viewModel.isInputRequested.observeAsState(initial = false)
        var input by remember { mutableStateOf("") }
        Column {
            Text(
                text = text,
                color = ConsoleOutput,
                style = ConsoleRegularTextStyle
            )
            if(inputRequested) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    textStyle = ConsoleRegularTextStyle.copy(color = ConsoleInput),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.submitInput(input)
                        input = ""
                    }), singleLine = true
                )
            }
        }
    }
}