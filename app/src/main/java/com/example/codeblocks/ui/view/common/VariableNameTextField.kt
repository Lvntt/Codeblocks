package com.example.codeblocks.ui.view.common

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
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
import com.example.codeblocks.ui.theme.BlockAccentedTextStyle
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.NestingColor
import com.example.codeblocks.ui.theme.TextFieldHeight
import com.example.codeblocks.ui.theme.TextFieldMinimumWidth
import com.example.codeblocks.ui.theme.TextFieldPadding

@Composable
fun VariableNameTextField(
    parameterName: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholderId: Int,
    modifier: Modifier = Modifier,
    isEditable: Boolean = false,
    isInBlockWithNesting: Boolean = false
) {
    var textFieldContent by remember { mutableStateOf(parameterName) }

    val containerColor = if (isInBlockWithNesting) {
        NestingColor.Nested.color
    } else {
        MaterialTheme.colorScheme.background
    }

    Box(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(TextFieldHeight)
            .clip(BlockElementShape)
            .background(containerColor)
            .padding(horizontal = TextFieldPadding),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            enabled = isEditable,
            value = textFieldContent,
            onValueChange = {
                textFieldContent = it
                onValueChange(it)
            },
            modifier = modifier.widthIn(TextFieldMinimumWidth, Dp.Infinity),
            singleLine = true,
            textStyle = BlockAccentedTextStyle.copy(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            ),
            decorationBox = { innerTextField ->
                if (textFieldContent.isEmpty()) {
                    Text(
                        text = stringResource(id = placeholderId),
                        color = Color.LightGray,
                        style = BlockRegularTextStyle.copy(textAlign = TextAlign.Center)
                    )
                }
                innerTextField()
            }
        )
    }
}