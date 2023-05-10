package com.example.codeblocks.ui

import com.example.codeblocks.R

object BottomNavItems {

    private const val EDITOR_BUTTON_NAME = "Editor"
    private const val CONSOLE_BUTTON_NAME = "Console"
    private const val OVERVIEW_BUTTON_NAME = "Overview"

    private val bottomNavItems = listOf(
        BottomNavItem(
            name = EDITOR_BUTTON_NAME,
            route = CodeblocksDestinations.EDITOR_ROUTE,
            iconId = R.drawable.editor_icon
        ),
        BottomNavItem(
            name = CONSOLE_BUTTON_NAME,
            route = CodeblocksDestinations.CONSOLE_ROUTE,
            iconId = R.drawable.console_icon
        ),
        BottomNavItem(
            name = OVERVIEW_BUTTON_NAME,
            route = CodeblocksDestinations.OVERVIEW_ROUTE,
            iconId = R.drawable.overview_icon
        )
    )

    fun getBottomNavItems() = bottomNavItems

}