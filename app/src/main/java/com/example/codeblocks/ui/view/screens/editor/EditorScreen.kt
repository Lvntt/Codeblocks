package com.example.codeblocks.ui.view.screens.editor

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.reorderable.ReorderableLazyListState
import com.example.codeblocks.reorderable.reorderable
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.theme.ProgressBarSize
import com.example.codeblocks.ui.view.blocks.StartBlock

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier,
    listState: ReorderableLazyListState,
    rowState: LazyListState
) {
    val blocks = viewModel.rootProgramBlocks
    Crossfade(targetState = viewModel.isLoading) {
        when (it.value) {
            true -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(ProgressBarSize))
                }
            }

            false -> {
                LazyRow(
                    state = rowState,
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = BlockPadding)
                ) {
                    item {

                        LazyColumn(
                            state = listState.listState,
                            modifier = modifier
                                .fillMaxSize()
                                .reorderable(listState),
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
                                reorderableState = listState
                            )
                        }
                    }
                }
            }
        }
    }
}
