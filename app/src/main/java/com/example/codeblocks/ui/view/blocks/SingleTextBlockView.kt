package com.example.codeblocks.ui.view.blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.NestingColor

@Composable
fun SingleTextBlockView(
    modifier: Modifier = Modifier,
    onAddBlockClick: () -> Unit = {},
    @StringRes descriptionStringRes: Int,
    isEditable: Boolean = true,
    isInBlockWithNesting: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }

    val containerColor = if (isInBlockWithNesting) {
        NestingColor.Container.color
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val onContainerColor = if (isInBlockWithNesting) {
        NestingColor.OnContainer.color
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (!isEditable) {
                    onAddBlockClick()
                }
            }
            .height(BlockHeight)
            .widthIn(BlockMinimumWidth, Dp.Infinity)
            .clip(BlockElementShape)
            .background(containerColor)
            .padding(BlockPadding)
    ) {
        Row(
            modifier = modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = descriptionStringRes),
                color = onContainerColor,
                style = BlockRegularTextStyle
            )
        }
    }
}