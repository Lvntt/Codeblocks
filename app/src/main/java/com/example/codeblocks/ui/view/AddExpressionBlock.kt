package com.example.codeblocks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.codeblocks.ui.theme.AddExpressionButtonSize
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockPadding

@Composable
fun AddExpressionBlock(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(AddExpressionButtonSize)
            .width(AddExpressionButtonSize)
            .aspectRatio(1f)
            .clip(BlockElementShape)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(BlockPadding)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    }
}