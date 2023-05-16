package com.example.codeblocks.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.Program
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.EqualityCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.NotEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.DivisionBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MinusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MultiplicationBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.PlusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.RemainderBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.domain.usecases.ClearConsoleUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.data.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.EmptyParameters
import com.example.codeblocks.presentation.block.parameters.OperatorExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.ItemPosition
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class CodeEditorViewModel(
    private val clearConsoleUseCase: ClearConsoleUseCase,
    private val writeToConsoleUseCase: WriteToConsoleUseCase
) : ViewModel() {

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
        EqualityCheckBlock::class to OperatorExpressionBlockParameters::class,
        LessCheckBlock::class to OperatorExpressionBlockParameters::class,
        LessOrEqualCheckBlock::class to OperatorExpressionBlockParameters::class,
        MoreCheckBlock::class to OperatorExpressionBlockParameters::class,
        MoreOrEqualCheckBlock::class to OperatorExpressionBlockParameters::class,
        NotEqualCheckBlock::class to OperatorExpressionBlockParameters::class,
        PrintToConsoleBlock::class to SingleExpressionParameter::class,
        ReadFromConsoleBlock::class to EmptyParameters::class
    )

    private val runtimeExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        writeToConsoleUseCase.writeErrorToConsole(throwable.stackTraceToString())
        writeToConsoleUseCase.writeOutputToConsole("\nProcess finished with exit code 1")
    }

    fun moveBlock(from: ItemPosition, to: ItemPosition) {
        _programBlocks.apply {
            if (to.index - 1 >= 0 && to.index - 1 < _programBlocks.size
                && from.index - 1 >= 0 && from.index - 1 < _programBlocks.size) {
                add(to.index - 1, removeAt(from.index - 1))
            }
        }
    }

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

    fun runProgram() {
        val program = Program()
        viewModelScope.launch(Dispatchers.IO + runtimeExceptionHandler) {
            programBlocks.forEach {
                val block = it.createBlock()
                program.blocks.add(block)
            }
            clearConsoleUseCase.clear()
            program.execute()
            writeToConsoleUseCase.writeOutputToConsole("\nProcess finished with exit code 0")
        }
    }

}