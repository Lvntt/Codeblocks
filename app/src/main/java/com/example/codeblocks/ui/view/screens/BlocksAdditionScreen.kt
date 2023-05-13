package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import kotlin.reflect.KClass

@Composable
fun BlocksAdditionScreen(
    availableBlocks: List<KClass<out Block>>,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CodeEditorViewModel
) {
    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(BlockPadding)
    ) {
        item {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(PaddingBetweenBlocks)
            ) {
                items(availableBlocks) { currentBlockClass ->
                    val onClick = {
                        viewModel.onAddBlockClick(currentBlockClass)
                        navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                    }
                    when (currentBlockClass) {
                        CreateVariableBlock::class -> {
                            VariableDeclarationBlock(
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }
                        SetVariableBlock::class -> {
                            VariableAssignmentBlock(
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }
                    }
                }
            }
        }
    }
}