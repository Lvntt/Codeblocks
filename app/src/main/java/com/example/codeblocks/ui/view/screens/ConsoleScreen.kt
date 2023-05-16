package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.codeblocks.ui.theme.ConsoleBackground
import com.example.codeblocks.ui.theme.ConsoleInput
import com.example.codeblocks.ui.theme.ConsoleOutput
import com.example.codeblocks.ui.theme.ConsoleRegularTextStyle

@Composable
fun ConsoleScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(ConsoleBackground),
        contentAlignment = Alignment.TopStart
    ) {
        var text by remember { mutableStateOf("") }
        Column {
            Text(
                text = "This is console screen",
                color = ConsoleOutput,
                style = ConsoleRegularTextStyle
            )
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = ConsoleRegularTextStyle.copy(color = ConsoleInput)
            )
        }
    }
}