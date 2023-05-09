package com.example.codeblocks.presentation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.codeblocks.R
import com.example.codeblocks.presentation.ui.theme.BlockAccentedTextStyle
import com.example.codeblocks.presentation.ui.theme.BlockElementShape
import com.example.codeblocks.presentation.ui.theme.BlockHeight
import com.example.codeblocks.presentation.ui.theme.BlockMinimumWidth
import com.example.codeblocks.presentation.ui.theme.BlockPadding
import com.example.codeblocks.presentation.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.presentation.ui.theme.InnerBlockElementStartPadding
import com.example.codeblocks.presentation.ui.theme.TextFieldHeight
import com.example.codeblocks.presentation.ui.theme.TextFieldMinimumWidth
import com.example.codeblocks.presentation.ui.theme.TextFieldPadding
import com.example.codeblocks.presentation.ui.theme.VariableTypeChoiceHeight
import com.example.codeblocks.presentation.ui.theme.VariableTypeChoiceWidth

@Composable
fun VariableDeclarationBlock(
    modifier: Modifier = Modifier
) {
    val availableVariableTypes = listOf("int", "boolean", "double", "float")
    var textFieldContent by remember { mutableStateOf("") }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }
    var dropdownMenuSelectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .height(BlockHeight)
            .widthIn(BlockMinimumWidth, Dp.Infinity)
            .clip(BlockElementShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(BlockPadding)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.create),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = BlockRegularTextStyle
                )
            }
            Box(
                modifier = modifier
                    .padding(start = InnerBlockElementStartPadding)
                    .height(VariableTypeChoiceHeight)
                    .width(VariableTypeChoiceWidth)
                    .clip(BlockElementShape)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = availableVariableTypes[dropdownMenuSelectedIndex],
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier
                        .clickable {
                        dropdownMenuExpanded = true
                    },
                    style = BlockAccentedTextStyle
                )
                DropdownMenu(
                    expanded = dropdownMenuExpanded,
                    onDismissRequest = {
                        dropdownMenuExpanded = false
                    }
                ) {
                    availableVariableTypes.forEachIndexed { index, s ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = s,
                                    style = BlockRegularTextStyle
                                )
                            },
                            onClick = {
                                dropdownMenuSelectedIndex = index
                                dropdownMenuExpanded = false
                            }
                        )
                    }
                }
            }
            Box(
                modifier = modifier.padding(start = InnerBlockElementStartPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.variable),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = BlockRegularTextStyle
                )
            }
            Box(
                modifier = modifier
                    .padding(start = InnerBlockElementStartPadding)
                    .width(IntrinsicSize.Min)
                    .height(TextFieldHeight)
                    .clip(BlockElementShape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = TextFieldPadding),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = textFieldContent,
                    onValueChange = {
                        textFieldContent = it
                    },
                    modifier = modifier.widthIn(TextFieldMinimumWidth, Dp.Infinity),
                    singleLine = true,
                    textStyle = BlockAccentedTextStyle.copy(textAlign = TextAlign.Center),
                    decorationBox = { innerTextField ->
                        if (textFieldContent.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.namePlaceholder),
                                color = Color.LightGray,
                                style = BlockRegularTextStyle.copy(textAlign = TextAlign.Center)
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    }
}