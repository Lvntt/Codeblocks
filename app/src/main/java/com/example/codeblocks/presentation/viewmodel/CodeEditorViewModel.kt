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
import com.example.codeblocks.domain.entity.blocks.conditional.ElseBlock
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.ListElementByIndexBlock
import com.example.codeblocks.domain.entity.blocks.expression.ListExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.CastBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.EqualityCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.NotEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.logic.AndBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.logic.OrBlock
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
import com.example.codeblocks.domain.entity.blocks.variable.list.AddElementToListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.CreateListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.InsertListElementBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.RemoveElementFromListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.SetListElementBlock
import com.example.codeblocks.domain.usecases.ClearConsoleUseCase
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.data.SimpleBlockData
import com.example.codeblocks.presentation.block.parameters.CastExpressionParameters
import com.example.codeblocks.presentation.block.parameters.EmptyParameters
import com.example.codeblocks.presentation.block.parameters.ForLoopBlockParameters
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.presentation.block.parameters.FunctionReturnParameters
import com.example.codeblocks.presentation.block.parameters.IfBlockParameters
import com.example.codeblocks.presentation.block.parameters.ListExpressionParameters
import com.example.codeblocks.presentation.block.parameters.TwoExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.presentation.block.parameters.ThreeExpressionBlockParameters
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
    private val _addBlockButtonMap: MutableMap<UUID, BlockWithNestingData> = mutableMapOf()
    private val _bottomBlockBorderMap: MutableMap<UUID, BlockWithNestingData> = mutableMapOf()

    private var _currentAddBlockCallback: (KClass<out Block>) -> Unit = {}

    private val blockTypeToParameter = mapOf(
        CreateVariableBlock::class to VariableDeclarationBlockParameters::class,
        SetVariableBlock::class to VariableAssignmentBlockParameters::class,
        VariableByNameBlock::class to StringExpressionParameter::class,
        VariableByValueBlock::class to StringExpressionParameter::class,
        PlusBlock::class to TwoExpressionBlockParameters::class,
        MinusBlock::class to TwoExpressionBlockParameters::class,
        DivisionBlock::class to TwoExpressionBlockParameters::class,
        MultiplicationBlock::class to TwoExpressionBlockParameters::class,
        RemainderBlock::class to TwoExpressionBlockParameters::class,
        EqualityCheckBlock::class to TwoExpressionBlockParameters::class,
        LessCheckBlock::class to TwoExpressionBlockParameters::class,
        LessOrEqualCheckBlock::class to TwoExpressionBlockParameters::class,
        MoreCheckBlock::class to TwoExpressionBlockParameters::class,
        MoreOrEqualCheckBlock::class to TwoExpressionBlockParameters::class,
        NotEqualCheckBlock::class to TwoExpressionBlockParameters::class,
        AndBlock::class to TwoExpressionBlockParameters::class,
        OrBlock::class to TwoExpressionBlockParameters::class,
        PrintToConsoleBlock::class to SingleExpressionParameter::class,
        ReadFromConsoleBlock::class to EmptyParameters::class,
        IfBlock::class to IfBlockParameters::class,
        WhileBlock::class to SingleExpressionParameter::class,
        DoWhileBlock::class to SingleExpressionParameter::class,
        BreakBlock::class to EmptyParameters::class,
        ContinueBlock::class to EmptyParameters::class,
        FunctionReturnBlock::class to FunctionReturnParameters::class,
        FunctionDeclaratorBlock::class to FunctionDeclarationParameters::class,
        FunctionCallBlock::class to FunctionCallParameters::class,
        ForBlock::class to ForLoopBlockParameters::class,
        ElseBlock::class to EmptyParameters::class,
        CastBlock::class to CastExpressionParameters::class,
        CreateListBlock::class to VariableDeclarationBlockParameters::class,
        AddElementToListBlock::class to TwoExpressionBlockParameters::class,
        RemoveElementFromListBlock::class to TwoExpressionBlockParameters::class,
        SetListElementBlock::class to ThreeExpressionBlockParameters::class,
        InsertListElementBlock::class to ThreeExpressionBlockParameters::class,
        ListElementByIndexBlock::class to TwoExpressionBlockParameters::class,
        ListExpressionBlock::class to ListExpressionParameters::class
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

    fun addNewBlockToList(blockType: KClass<out Block>, addTo: MutableList<BlockData>, parentBlockId: UUID?) {
        addTo.add(createBlockDataByType(blockType) as BlockData)
        addTo.last().parentBlockId = parentBlockId
        addTo.last().parentBlockListIndex = addTo.lastIndex
        addBlockToMap(addTo.last())
        if (addTo.last() is BlockWithNestingData) {
            registerAddButton(addTo.last() as BlockWithNestingData)
            registerBlockWithNestingBottomBorder(addTo.last() as BlockWithNestingData)
        }
    }

    fun addElseBlock(ifBlock: BlockWithNestingData) {
        if (ifBlock.blockClass != IfBlock::class || ifBlock.blockParametersData !is IfBlockParameters) return
        ifBlock.blockParametersData.elseBlock =
            createBlockDataByType(ElseBlock::class) as BlockWithNestingData?
        nestedBlockListByBlockId(ifBlock.parentBlockId)?.removeAt(ifBlock.parentBlockListIndex)
        nestedBlockListByBlockId(ifBlock.parentBlockId)?.add(ifBlock.parentBlockListIndex, ifBlock)
        val elseBlock = ifBlock.blockParametersData.elseBlock ?: return
        addBlockToMap(elseBlock)
        registerAddButton(elseBlock)
        _bottomBlockBorderMap[elseBlock.bottomBorderId] = ifBlock
    }

    fun removeBlockFromList(id: UUID) {
        val block = _blockMap[id] ?: return
        val parentContainer = nestedBlockListByBlockId(block.parentBlockId) ?: return

        parentContainer.removeAt(block.parentBlockListIndex)
        if (block is BlockWithNestingData) {
            unregisterAddButton(block.addBlockButtonId)
            unregisterBlockWithNestingBottomBorder(block.bottomBorderId)
        }
        for (blockIndex in block.parentBlockListIndex..parentContainer.lastIndex) {
            parentContainer[blockIndex].parentBlockListIndex--
        }
        removeBlockFromMap(id)

        if(block.blockClass == IfBlock::class) {
            val elseBlock = (block.blockParametersData as IfBlockParameters).elseBlock ?: return
            unregisterAddButton(elseBlock.addBlockButtonId)
            unregisterBlockWithNestingBottomBorder(elseBlock.bottomBorderId)
        }
    }

    private fun registerAddButton(blockWithNestingData: BlockWithNestingData) {
        _addBlockButtonMap[blockWithNestingData.addBlockButtonId] =
            blockWithNestingData
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

    private fun nestedBlockListByBlockId(id: UUID?): MutableList<BlockData>? =
        if(id == null) {
            rootProgramBlocks
        } else if(_blockMap[id]!=null && _blockMap[id] is BlockWithNestingData) {
            (_blockMap[id] as BlockWithNestingData).nestedBlocksData
        } else {
            null
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

    fun setBlockExpanded(expanded: Boolean, block: BlockWithNestingData) {
        block.expanded = expanded
        nestedBlockListByBlockId(block.parentBlockId)?.removeAt(block.parentBlockListIndex)
        nestedBlockListByBlockId(block.parentBlockId)?.add(
            block.parentBlockListIndex, block
        )
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
        val elseBlockIsPresent =
            blockWithNesting.blockClass == IfBlock::class
                    && (blockWithNesting.blockParametersData as IfBlockParameters).elseBlock != null
                    && blockWithNesting.bottomBorderId == blockWithNestingBottomBorderId
        val parentBlockContainer =
            if (elseBlockIsPresent) {
                ((blockWithNesting.blockParametersData as IfBlockParameters).elseBlock as BlockWithNestingData).nestedBlocksData
            } else {
                nestedBlockListByBlockId(blockWithNesting.parentBlockId) ?: return false
            }
        val nestedBlockContainer = nestedBlockListByBlockId(toExtract.parentBlockId) ?: return false

        val toInsertIndex = if (elseBlockIsPresent) 0 else blockWithNesting.parentBlockListIndex + 1
        nestedBlockContainer.removeAt(nestedBlockContainer.lastIndex)
        parentBlockContainer.add(toInsertIndex, toExtract)

        for (blockIndex in toInsertIndex + 1..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex++
        }
        if(!elseBlockIsPresent) {
            toExtract.parentBlockId = blockWithNesting.parentBlockId
        } else {
            toExtract.parentBlockId = (blockWithNesting.blockParametersData as IfBlockParameters).elseBlock?.id
        }
        toExtract.parentBlockListIndex = toInsertIndex
        return true
    }

    private fun tryInsertIntoBlockWithNestingFromBottom(
        blockWithNestingAddButtonId: Any,
        toInsert: BlockData
    ): Boolean {
        val blockWithNesting = _addBlockButtonMap[blockWithNestingAddButtonId] ?: return false
        val nestedBlockContainer = blockWithNesting.nestedBlocksData
        val parentBlockContainer = nestedBlockListByBlockId(toInsert.parentBlockId) ?: return false

        parentBlockContainer.removeAt(toInsert.parentBlockListIndex)
        nestedBlockContainer.add(toInsert)

        for (blockIndex in toInsert.parentBlockListIndex..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex--
        }
        toInsert.parentBlockId = blockWithNesting.id
        toInsert.parentBlockListIndex = nestedBlockContainer.lastIndex
        return true
    }

    private fun tryInsertIntoBlockWithNestingFromTop(
        blockWithNesting: BlockWithNestingData,
        toInsert: BlockData
    ): Boolean {
        val nestedBlockContainer = blockWithNesting.nestedBlocksData
        val parentBlockContainer = nestedBlockListByBlockId(toInsert.parentBlockId) ?: return false

        nestedBlockContainer.add(0, toInsert)
        parentBlockContainer.removeAt(toInsert.parentBlockListIndex)

        for (blockIndex in 1..nestedBlockContainer.lastIndex) {
            nestedBlockContainer[blockIndex].parentBlockListIndex++
        }
        for (blockIndex in toInsert.parentBlockListIndex..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex--
        }
        toInsert.parentBlockId = blockWithNesting.id
        toInsert.parentBlockListIndex = 0
        return true
    }

    private fun tryExtractFromBlockWithNestingAtTop(
        from: BlockWithNestingData,
        toExtract: BlockData
    ): Boolean {
        val parentBlockContainer = nestedBlockListByBlockId(from.parentBlockId) ?: return false
        val nestedBlockContainer = from.nestedBlocksData

        parentBlockContainer.add(from.parentBlockListIndex, toExtract)
        nestedBlockContainer.removeAt(0)

        for (blockIndex in from.parentBlockListIndex + 1..parentBlockContainer.lastIndex) {
            parentBlockContainer[blockIndex].parentBlockListIndex++
        }
        for (blockIndex in 0..nestedBlockContainer.lastIndex) {
            nestedBlockContainer[blockIndex].parentBlockListIndex--
        }
        toExtract.parentBlockId = from.parentBlockId
        toExtract.parentBlockListIndex = from.parentBlockListIndex - 1
        return true
    }

    private fun trySwapTwoElements(
        firstBlockData: BlockData,
        secondBlockData: BlockData
    ): Boolean {
        val firstBlockParentList = nestedBlockListByBlockId(firstBlockData.parentBlockId) ?: return false
        val secondBlockParentList = nestedBlockListByBlockId(secondBlockData.parentBlockId) ?: return false
        val firstBlockDataListIndex = firstBlockData.parentBlockListIndex
        val firstBlockParentId = firstBlockData.parentBlockId

        firstBlockParentList.removeAt(firstBlockData.parentBlockListIndex)
        firstBlockParentList.add(firstBlockData.parentBlockListIndex, secondBlockData)
        secondBlockParentList.removeAt(secondBlockData.parentBlockListIndex)
        secondBlockParentList.add(secondBlockData.parentBlockListIndex, firstBlockData)

        firstBlockData.parentBlockId = secondBlockData.parentBlockId
        secondBlockData.parentBlockId = firstBlockParentId
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