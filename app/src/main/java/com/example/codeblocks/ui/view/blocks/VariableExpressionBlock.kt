package com.example.codeblocks.ui.view.blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.ui.view.common.VariableNameTextField

@Composable
fun VariableExpressionBlock(
    @StringRes placeholderId: Int,
    modifier: Modifier = Modifier,
    parameters: StringExpressionParameter = StringExpressionParameter(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                if (!isEditable) {
                    onAddBlockClick()
                }
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        VariableNameTextField(
            parameterName = parameters.name,
            onValueChange = {
                parameters.name = it
            },
            placeholderId = placeholderId,
            isEditable = isEditable
        )
    }
}