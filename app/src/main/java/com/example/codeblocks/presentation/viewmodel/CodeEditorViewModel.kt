package com.example.codeblocks.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.presentation.block.BlockData
import com.example.codeblocks.presentation.block.BlockWithNestingData
import com.example.codeblocks.presentation.block.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class CodeEditorViewModel : ViewModel() {

    private val _programBlocks: MutableList<BlockData> = mutableStateListOf()
    val programBlocks: List<BlockData> = _programBlocks

    private val blockTypeToParameter = mapOf(
        CreateVariableBlock::class to VariableDeclarationBlockParameters::class
    )

    fun onAddBlockClick(blockToCreate: KClass<out Block>) {
        val blockParameters = blockTypeToParameter[blockToCreate]
        if (blockParameters != null) {
            _programBlocks.add(
                if (blockToCreate.isSubclassOf(BlockWithNesting::class)) {
                    BlockWithNestingData(
                        blockToCreate,
                        blockParameters.createInstance()
                    )
                } else {
                    SimpleBlockData(
                        blockToCreate,
                        blockParameters.createInstance()
                    )
                }
            )
        } else {
            // TODO show error message
        }
    }

}