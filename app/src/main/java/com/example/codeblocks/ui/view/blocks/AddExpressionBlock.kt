package com.example.codeblocks.ui.view.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.codeblocks.ui.theme.AddExpressionButtonSize
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.NestingColor

@Composable
fun AddExpressionBlock(
    modifier: Modifier = Modifier,
    isEditable: Boolean = true,
    onClick: () -> Unit,
    isInBlockWithNesting: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    var customModifier = modifier
    if (isEditable) {
        customModifier = customModifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
    }

    val containerColor = if (isInBlockWithNesting) {
        NestingColor.Nested.color
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }

    val onContainerColor = if (isInBlockWithNesting) {
        if(!isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            NestingColor.OnNested.color
        }
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    Box(
        modifier = customModifier
            .height(AddExpressionButtonSize)
            .width(AddExpressionButtonSize)
            .aspectRatio(1f)
            .clip(BlockElementShape)
            .background(containerColor)
            .padding(BlockPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                tint = onContainerColor,
                contentDescription = null
            )
        }
    }
}