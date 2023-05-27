package com.example.codeblocks.ui.view.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.CardAccentedTextStyle
import com.example.codeblocks.ui.theme.CardPadding
import com.example.codeblocks.ui.theme.CardRegularTextStyle
import com.example.codeblocks.ui.theme.CardTextPadding
import com.example.codeblocks.ui.theme.CardTitleTextStyle
import com.example.codeblocks.ui.theme.SpacerBetweenCardsHeight
import java.io.File
import java.text.DateFormat

@Composable
fun SavedProgramsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    savedPrograms: State<Array<out File>?>,
    getPrograms: (Context) -> Unit,
    onProgramClick: (File) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        getPrograms(context)
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(id = R.string.yourSavedPrograms),
                style = CardTitleTextStyle
            )
            Spacer(modifier = modifier.height(SpacerBetweenCardsHeight))
        }

        if (savedPrograms.value != null) {
            items(savedPrograms.value!!) { savedProgram ->
                SavedProgram(
                    navController = navController,
                    program = savedProgram,
                    onProgramClick = onProgramClick
                )
                Spacer(modifier = modifier.height(SpacerBetweenCardsHeight))
            }
        } else {
            item {
                Text(
                    text = stringResource(id = R.string.noSavedPrograms),
                    style = CardAccentedTextStyle
                )
            }
        }
    }
}

@Composable
fun SavedProgram(
    navController: NavController,
    program: File,
    onProgramClick: (File) -> Unit,
    modifier: Modifier = Modifier
) {
    val formatter = DateFormat.getDateTimeInstance()

    Card(
        modifier = modifier
            .clickable {
                onProgramClick(program)
                navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
            }
            .padding(horizontal = CardPadding)
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier.padding(CardTextPadding),
            text = program.name,
            style = CardAccentedTextStyle
        )
        Text(
            modifier = modifier.padding(CardTextPadding),
            text = formatter.format(program.lastModified()),
            style = CardRegularTextStyle
        )
    }
}