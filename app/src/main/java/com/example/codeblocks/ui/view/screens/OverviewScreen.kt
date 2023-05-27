package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.codeblocks.R
import com.example.codeblocks.ui.theme.CardAccentedTextStyle
import com.example.codeblocks.ui.theme.CardRegularTextStyle
import com.example.codeblocks.ui.theme.CardTitleTextStyle
import com.example.codeblocks.ui.theme.SpacerBetweenCardsHeight

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.codeblocksApp),
            style = CardTitleTextStyle
        )
        Text(
            text = stringResource(id = R.string.madeBy),
            style = CardRegularTextStyle
        )
        Spacer(modifier = modifier.height(SpacerBetweenCardsHeight))
        Text(
            text = stringResource(id = R.string.ruslanGafarov),
            style = CardAccentedTextStyle
        )
        Text(
            text = stringResource(id = R.string.matveySeregin),
            style = CardAccentedTextStyle
        )
        Text(
            text = stringResource(id = R.string.yuriyEliseev),
            style = CardAccentedTextStyle
        )
    }
}