package com.example.codeblocks.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.Program
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
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
import com.example.codeblocks.domain.entity.blocks.function.FunctionCallBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.blocks.loop.BreakBlock
import com.example.codeblocks.domain.entity.blocks.loop.ContinueBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.domain.entity.blocks.loop.ForBlock
import com.example.codeblocks.domain.entity.blocks.loop.WhileBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.domain.usecases.ClearConsoleUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.data.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.EmptyParameters
import com.example.codeblocks.presentation.block.parameters.ForLoopBlockParameters
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.presentation.block.parameters.FunctionReturnParameters
import com.example.codeblocks.presentation.block.parameters.OperatorExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.reorderable.ItemPosition
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.abs
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class CodeEditorViewModel(
    private val clearConsoleUseCase: ClearConsoleUseCase,
    private val writeToConsoleUseCase: WriteToConsoleUseCase
) : ViewModel() {

    val rootProgramBlocks: MutableList<BlockData> = mutableStateListOf()
    val rootAddBlockButtonId: UUID = UUID.randomUUID()

    private val _isDeleteMode: MutableState<Boolean> = mutableStateOf(false)
    val isDeleteMode: State<Boolean> get() = _isDeleteMode

    private val _blockMap: MutableMap<UUID, BlockData> = mutableMapOf()
    private val _addBlockButtonMap: MutableMap<UUID, MutableList<BlockData>> = mutableMapOf()
    private val _bottomBlockBorderMap: MutableMap<UUID, BlockWithNestingData> = mutableMapOf()

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
        ReadFromConsoleBlock::class to EmptyParameters::class,
        IfBlock::class to SingleExpressionParameter::class,
        WhileBlock::class to SingleExpressionParameter::class,
        DoWhileBlock::class to SingleExpressionParameter::class,
        BreakBlock::class to EmptyParameters::class,
        ContinueBlock::class to EmptyParameters::class,
        FunctionReturnBlock::class to FunctionReturnParameters::class,
        FunctionDeclaratorBlock::class to FunctionDeclarationParameters::class,
        FunctionCallBlock::class to FunctionCallParameters::class,
        ForBlock::class to ForLoopBlockParameters::class
    )

    private val runtimeExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        writeToConsoleUseCase.writeErrorToConsole(throwable.stackTraceToString())
        writeToConsoleUseCase.writeOutputToConsole("\nProcess finished with exit code 1")
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
                    type, blockParameters.createInstance()
                )
            } else if (type.isSubclassOf(ExpressionBlock::class)) {
                ExpressionBlockData(
                    type as KClass<out ExpressionBlock>,
                    blockParameters.createInstance()
                )
            } else {
                SimpleBlockData(
                    type, blockParameters.createInstance()
                )
            }
        } else {
            return null
            // TODO show error message
        }
    }

    fun addNewBlockToList(blockType: KClass<out Block>, addTo: MutableList<BlockData>) {
        addTo.add(createBlockDataByType(blockType) as BlockData)
        addTo.last().parentBlockList = addTo
        addTo.last().parentBlockListIndex = addTo.lastIndex
        addBlockToMap(addTo.last())
        if (addTo.last() is BlockWithNestingData) {
            registerAddButton((addTo.last() as BlockWithNestingData))
            registerBlockWithNestingBottomBorder((addTo.last() as BlockWithNestingData))
        }
    }

    fun removeBlockFromList(id: UUID) {
        val block = _blockMap[id] ?: return
        val parentContainer = block.parentBlockList ?: return

        parentContainer.removeAt(block.parentBlockListIndex)
        if (block is BlockWithNestingData) {
            unregisterAddButton(block.addBlockButtonId)
            unregisterBlockWithNestingBottomBorder(block.bottomBorderId)
        }
        for (blockIndex in block.parentBlockListIndex..parentContainer.lastIndex) {
            parentContainer[blockIndex].parentBlockListIndex--
        }
        removeBlockFromMap(id)
    }

    private fun registerAddButton(blockWithNestingData: BlockWithNestingData) {
        _addBlockButtonMap[blockWithNestingData.addBlockButtonId] =
            blockWithNestingData.nestedBlocksData
    }

    private fun unregisterAddButton(id: UUID) {
        _addBlockButtonMap.remove(id)
    }

    private fun registerBlockWithNestingBottomBorder(blockWithNestingData: BlockWithNestingData) {
        _bottomBlockBorderMap[blockWithNestingData.bottomBorderId] =
            blockWithNestingData
    }

    private fun unregisterBlockWithNestingBottomBorder(id: UUID) {
        _bottomBlockBorderMap.remove(id)
    }

    private fun addBlockToMap(blockData: BlockData) {
        _blockMap[blockData.id] = blockData
    }

    private fun removeBlockFromMap(id: UUID) {
        _blockMap.remove(id)
    }

    fun moveBlock(from: ItemPosition, to: ItemPosition): Boolean {
        val fromUUID = from.key ?: return false
        val toUUID = to.key ?: return false
        val fromBlockData = _blockMap[fromUUID] ?: return false
        if (abs(from.index - to.index) > 2) return false
        val toBlockData = _blockMap[toUUID]
            ?: return tryToSwapWithNonBlockElement(from, to, toUUID, fromBlockData)
        if (abs(from.index - to.index) > 1) return false
        if (toBlockData is BlockWithNestingData && from.index < to.index) {
            return tryInsertIntoBlockWithNestingFromTop(toBlockData, fromBlockData)
        }
        if (toBlockData is BlockWithNestingData && from.index > to.index) {
            return tryExtractFromBlockWithNestingAtTop(toBlockData, fromBlockData)
        }
        return trySwapTwoElements(fromBlockData, toBlockData)
    }

    private fun tryToSwapWithNonBlockElement(
        from: ItemPosition,
        to: ItemPosition,
        toUUID: Any,
        fromBlockData: BlockData
    ): Boolean {
        return if (_addBlockButtonMap[toUUID] != null && from.index > to.index) {
            tryInsertIntoBlockWithNestingFromBottom(toUUID, fromBlockData)
        } else if (_bottomBlockBorderMap[toUUID] != null && from.index < to.index) {
            tryExtractFromBlockWithNestingAtBottom(toUUID, fromBlockData)
        } else {
            false
        }
    }

    private fun tryExtractFromBlockWithNestingAtBottom(
        blockWithNestingBottomBorderId: Any,
        toExtract: BlockData
    ): Boolean {
        val blockWithNesting = _bottomBlockBorderMap[blockWithNestingBottomBorderId] ?: return false
        val parentBlockContainer = blockWithNesting.parentBlockList ?: return false
        val nestedBlockContainer = toExtract.parentBlockList ?: return false

        nestedBlockContainer.removeAt(nestedBlockContainer.lastIndex)
        parentBlockContainer.add(blockWithNesting.parentBlockListIndex + 1, toExtract)

        for (blockIndex in blockWithNesting.parentBlockListIndex + 2..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex++
        }
        toExtract.parentBlockList = parentBlockContainer
        toExtract.parentBlockListIndex = blockWithNesting.parentBlockListIndex + 1
        return true
    }

    private fun tryInsertIntoBlockWithNestingFromBottom(
        blockWithNestingAddButtonId: Any,
        toInsert: BlockData
    ): Boolean {
        val nestedBlockContainer = _addBlockButtonMap[blockWithNestingAddButtonId] ?: return false
        val parentBlockContainer = toInsert.parentBlockList ?: return false

        parentBlockContainer.removeAt(toInsert.parentBlockListIndex)
        nestedBlockContainer.add(toInsert)

        for (blockIndex in toInsert.parentBlockListIndex..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex--
        }
        toInsert.parentBlockList = nestedBlockContainer
        toInsert.parentBlockListIndex = nestedBlockContainer.lastIndex
        return true
    }

    private fun tryInsertIntoBlockWithNestingFromTop(
        blockWithNesting: BlockWithNestingData,
        toInsert: BlockData
    ): Boolean {
        val nestedBlockContainer = blockWithNesting.nestedBlocksData
        val parentBlockContainer = toInsert.parentBlockList ?: return false

        nestedBlockContainer.add(0, toInsert)
        parentBlockContainer.removeAt(toInsert.parentBlockListIndex)

        for (blockIndex in 1..nestedBlockContainer.lastIndex) {
            nestedBlockContainer[blockIndex].parentBlockListIndex++
        }
        for (blockIndex in toInsert.parentBlockListIndex..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex--
        }
        toInsert.parentBlockList = nestedBlockContainer
        toInsert.parentBlockListIndex = 0
        return true
    }

    private fun tryExtractFromBlockWithNestingAtTop(
        from: BlockWithNestingData,
        toExtract: BlockData
    ): Boolean {
        val parentBlockContainer = from.parentBlockList ?: return false
        val nestedBlockContainer = from.nestedBlocksData

        parentBlockContainer.add(from.parentBlockListIndex, toExtract)
        nestedBlockContainer.removeAt(0)

        for (blockIndex in from.parentBlockListIndex + 1..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex++
        }
        for (blockIndex in 0..nestedBlockContainer.lastIndex) {
            nestedBlockContainer[blockIndex].parentBlockListIndex--
        }
        toExtract.parentBlockList = parentBlockContainer
        toExtract.parentBlockListIndex = from.parentBlockListIndex - 1
        return true
    }

    private fun trySwapTwoElements(
        firstBlockData: BlockData,
        secondBlockData: BlockData
    ): Boolean {
        val firstBlockParentList = firstBlockData.parentBlockList ?: return false
        val secondBlockParentList = secondBlockData.parentBlockList ?: return false
        val firstBlockDataListIndex = firstBlockData.parentBlockListIndex

        firstBlockParentList.removeAt(firstBlockData.parentBlockListIndex)
        firstBlockParentList.add(firstBlockData.parentBlockListIndex, secondBlockData)
        secondBlockParentList.removeAt(secondBlockData.parentBlockListIndex)
        secondBlockParentList.add(secondBlockData.parentBlockListIndex, firstBlockData)

        firstBlockData.parentBlockList = secondBlockParentList
        secondBlockData.parentBlockList = firstBlockParentList
        firstBlockData.parentBlockListIndex = secondBlockData.parentBlockListIndex
        secondBlockData.parentBlockListIndex = firstBlockDataListIndex
        return true
    }

    fun runProgram() {
        val program = Program()
        viewModelScope.launch(Dispatchers.IO + runtimeExceptionHandler) {
            clearConsoleUseCase()
            rootProgramBlocks.forEach {
                val block = it.createBlock()
                program.blocks.add(block)
            }
            program.execute()
            writeToConsoleUseCase.writeOutputToConsole("\nProcess finished with exit code 0")
        }
    }

    fun changeDeleteMode() {
        _isDeleteMode.value = !_isDeleteMode.value
    }

}