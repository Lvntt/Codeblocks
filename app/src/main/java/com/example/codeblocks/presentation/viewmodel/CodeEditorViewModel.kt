package com.example.codeblocks.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.DivisionBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MinusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MultiplicationBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.PlusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.RemainderBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.data.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.OperatorExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class CodeEditorViewModel : ViewModel() {

    private val _programBlocks: MutableList<BlockData> = mutableStateListOf()
    val programBlocks: List<BlockData> = _programBlocks

    private var _currentAddBlockCallback: (KClass<out Block>) -> Unit = {}

    private val blockTypeToParameter = mapOf(
        CreateVariableBlock::class to VariableDeclarationBlockParameters::class,
        SetVariableBlock::class to VariableAssignmentBlockParameters::class,
        VariableByNameBlock::class to StringExpressionParameter::class,
        VariableByValueBlock::class to StringExpressionParameter::class,
        PlusBlock::class to OperatorExpressionBlockParameters::class,
        MinusBlock::class to OperatorExpressionBlockParameters::class,
        DivisionBlock::class to OperatorExpressionBlockParameters::class,
        MultiplicationBlock::class to OperatorExpressionBlockParameters::class,
        RemainderBlock::class to OperatorExpressionBlockParameters::class,
    )

    fun setAddBlockCallback(callback: (KClass<out Block>) -> Unit) {
        _currentAddBlockCallback = callback
    }

    fun executeCallback(type: KClass<out Block>) {
        _currentAddBlockCallback(type)
        _currentAddBlockCallback = {}
    }

    fun createBlockDataByType(type: KClass<out Block>): BlockData? {
        val blockParameters = blockTypeToParameter[type]
        if (blockParameters != null) {
            return if (type.isSubclassOf(BlockWithNesting::class)) {
                BlockWithNestingData(
                    type,
                    blockParameters.createInstance()
                )
            } else if (type.isSubclassOf(ExpressionBlock::class)) {
                ExpressionBlockData(
                    type as KClass<out ExpressionBlock>,
                    blockParameters.createInstance()
                )
            } else {
                SimpleBlockData(
                    type,
                    blockParameters.createInstance()
                )
            }
        } else {
            return null
            // TODO show error message
        }
    }

    fun onAddBlockClick(blockToCreate: KClass<out Block>) {
        val block = createBlockDataByType(blockToCreate)
        if (block != null) {
            _programBlocks.add(block)
        }
    }

}