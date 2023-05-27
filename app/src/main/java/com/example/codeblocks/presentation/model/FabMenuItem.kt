package com.example.codeblocks.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class FabMenuItem(
    @StringRes
    val description: Int,
    val icon: ImageVector
)
