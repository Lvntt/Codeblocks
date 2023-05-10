package com.example.codeblocks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.codeblocks.R
import com.example.codeblocks.ui.theme.BlockAccentedTextStyle
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.SmallBlockMinimumWidth

@Composable
fun StartBlock(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(BlockHeight)
            .widthIn(SmallBlockMinimumWidth, Dp.Infinity)
            .clip(BlockElementShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(BlockPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.start),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = BlockAccentedTextStyle
        )
    }
}