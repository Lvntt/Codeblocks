package com.example.codeblocks.ui

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes
    val iconId: Int
)