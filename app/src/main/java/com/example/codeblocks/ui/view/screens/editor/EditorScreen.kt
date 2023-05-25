package com.example.codeblocks.ui.view.screens.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.reorderable.rememberReorderableLazyListState
import com.example.codeblocks.reorderable.reorderable
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.StartBlock

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier,
) {
    val blocks = viewModel.rootProgramBlocks

    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = BlockPadding)
    ) {
        item {
            val state = rememberReorderableLazyListState(onMove = viewModel::moveBlock)

            LazyColumn(
                state = state.listState,
                modifier = modifier
                    .fillMaxSize()
                    .reorderable(state),
                verticalArrangement = Arrangement.spacedBy(PaddingBetweenBlocks),
                contentPadding = PaddingValues(vertical = BlockPadding)
            ) {
                item {
                    StartBlock()
                }

                nestedBlocks(
                    navController = navController,
                    viewModel = viewModel,
                    addBlockId = viewModel.rootAddBlockButtonId,
                    nestedBlocks = blocks,
                    reorderableState = state
                )
            }
        }
    }
}