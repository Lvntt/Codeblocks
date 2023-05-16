package com.example.codeblocks.ui.view.blocks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.codeblocks.R
import com.example.codeblocks.ui.theme.BlockRegularTextStyle

@Composable
fun InputFromConsoleBlock(
    modifier: Modifier = Modifier,
    onAddBlockClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onAddBlockClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.readFromConsole),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = BlockRegularTextStyle
        )
    }
}