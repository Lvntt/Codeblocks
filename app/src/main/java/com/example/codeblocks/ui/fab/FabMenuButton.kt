package com.example.codeblocks.ui.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.codeblocks.presentation.model.FabMenuItem

@Composable
fun FabMenuButton(
    fabMenuItem: FabMenuItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = fabMenuItem.icon,
                contentDescription = stringResource(fabMenuItem.description)
            )
        },
        text = {
            Text(text = stringResource(fabMenuItem.description))
        }
    )
}