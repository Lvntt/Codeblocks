package com.example.codeblocks.ui

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.ListElementByIndexBlock
import com.example.codeblocks.domain.entity.blocks.expression.ListExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.ListSizeBlock
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
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

object AvailableBlocks {

    val availableBlocks: List<KClass<out Block>> = listOf(
        CreateVariableBlock::class,
        SetVariableBlock::class,
        PrintToConsoleBlock::class,
        IfBlock::class,
        WhileBlock::class,
        DoWhileBlock::class,
        BreakBlock::class,
        ContinueBlock::class,
        FunctionReturnBlock::class,
        FunctionDeclaratorBlock::class,
        FunctionCallBlock::class,
        ForBlock::class,
        CreateListBlock::class,
        AddElementToListBlock::class,
        RemoveElementFromListBlock::class,
        SetListElementBlock::class,
        InsertListElementBlock::class
    )

    val availableBlocksWithoutNesting = availableBlocks.filter { !(it.isSubclassOf(BlockWithNesting::class)) }

    val availableExpressions: List<KClass<out ExpressionBlock>> = listOf(
        VariableByNameBlock::class,
        VariableByValueBlock::class,
        PlusBlock::class,
        MinusBlock::class,
        DivisionBlock::class,
        MultiplicationBlock::class,
        RemainderBlock::class,
        EqualityCheckBlock::class,
        LessCheckBlock::class,
        LessOrEqualCheckBlock::class,
        MoreCheckBlock::class,
        MoreOrEqualCheckBlock::class,
        NotEqualCheckBlock::class,
        AndBlock::class,
        OrBlock::class,
        CastBlock::class,
        ReadFromConsoleBlock::class,
        FunctionCallBlock::class,
        ListElementByIndexBlock::class,
        ListExpressionBlock::class,
        ListSizeBlock::class
    )

}