package com.example.codeblocks.presentation.ui

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes
    val iconId: Int
)