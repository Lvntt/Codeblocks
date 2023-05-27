package com.example.codeblocks.ui.navigation

import com.example.codeblocks.R

object BottomNavItems {

    private const val EDITOR_BUTTON_NAME = "Editor"
    private const val CONSOLE_BUTTON_NAME = "Console"
    private const val OVERVIEW_BUTTON_NAME = "Overview"
    private const val SAVED_PROGRAMS_BUTTON_NAME = "Saved programs"

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
            name = SAVED_PROGRAMS_BUTTON_NAME,
            route = CodeblocksDestinations.SAVED_PROGRAMS_ROUTE,
            iconId = R.drawable.save_icon
        ),
        BottomNavItem(
            name = OVERVIEW_BUTTON_NAME,
            route = CodeblocksDestinations.OVERVIEW_ROUTE,
            iconId = R.drawable.overview_icon
        )
    )

    fun getBottomNavItems() = bottomNavItems

}