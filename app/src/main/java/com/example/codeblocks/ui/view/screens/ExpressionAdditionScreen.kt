package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.ExpressionBlockHeight
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.theme.SmallBlockMinimumWidth
import com.example.codeblocks.ui.view.blocks.VariableByNameExpressionBlock
import com.example.codeblocks.ui.view.blocks.VariableByValueExpressionBlock
import kotlin.reflect.KClass

@Composable
fun ExpressionAdditionScreen(
    availableExpressions: List<KClass<out ExpressionBlock>>,
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
                items(availableExpressions) { currentBlockClass ->
                    val onClick: (KClass<out ExpressionBlock>) -> Unit = {
                        viewModel.executeCallback(it)
                        navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                    }
                    Box(
                        modifier = modifier
                            .height(ExpressionBlockHeight)
                            .width(SmallBlockMinimumWidth)
                            .clip(BlockElementShape)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(BlockPadding)
                    ) {
                        when (currentBlockClass) {
                            VariableByNameBlock::class -> {
                                VariableByNameExpressionBlock(
                                    onAddBlockClick = {
                                        onClick(currentBlockClass)
                                    },
                                    isEditable = false
                                )
                            }
                            VariableByValueBlock::class -> {
                                VariableByValueExpressionBlock(
                                    onAddBlockClick = {
                                        onClick(currentBlockClass)
                                    },
                                    isEditable = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}