package com.example.codeblocks.ui.view.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
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
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.parameters.CastExpressionParameters
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.presentation.block.parameters.OperatorExpressionBlockParameters
import com.example.codeblocks.presentation.block.parameters.StringExpressionParameter
import com.example.codeblocks.ui.view.blocks.CastExpressionBlock
import com.example.codeblocks.ui.view.blocks.FunctionCallBlock
import com.example.codeblocks.ui.view.blocks.InputFromConsoleBlock
import com.example.codeblocks.ui.view.blocks.OperatorExpressionBlock
import com.example.codeblocks.ui.view.blocks.VariableExpressionBlock
import kotlin.reflect.KClass

@Composable
fun ComposableByExpressionBlockClass(
    navController: NavController,
    parametersExpression: ExpressionBlockData,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit,
    createBlockDataByType: (KClass<out Block>) -> BlockData?
) {
    when (parametersExpression.blockClass) {
        VariableByNameBlock::class -> {
            VariableExpressionBlock(
                placeholderId = R.string.namePlaceholder,
                parameters = parametersExpression.blockParametersData as StringExpressionParameter
            )
        }

        VariableByValueBlock::class -> {
            VariableExpressionBlock(
                placeholderId = R.string.valuePlaceholder,
                parameters = parametersExpression.blockParametersData as StringExpressionParameter
            )
        }

        PlusBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.additionOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        MinusBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.subtractionOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        DivisionBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.divisionOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        MultiplicationBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.multiplicationOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        RemainderBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.remainderOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        EqualityCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.equalityOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        LessCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.lessOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        LessOrEqualCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.lessOrEqualOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        MoreCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.greaterOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        MoreOrEqualCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.greaterOrEqualOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        NotEqualCheckBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.inequalityOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        AndBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.andOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }
        
        OrBlock::class -> {
            OperatorExpressionBlock(
                navController = navController,
                blockOperator = stringResource(id = R.string.orOperator),
                parameters = parametersExpression.blockParametersData as OperatorExpressionBlockParameters,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        ReadFromConsoleBlock::class -> {
            InputFromConsoleBlock()
        }

        FunctionCallBlock::class -> {
            FunctionCallBlock(
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = parametersExpression.blockParametersData as FunctionCallParameters
            )
        }
        CastBlock::class -> {
            CastExpressionBlock(
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = parametersExpression.blockParametersData as CastExpressionParameters
            )
        }
    }
}