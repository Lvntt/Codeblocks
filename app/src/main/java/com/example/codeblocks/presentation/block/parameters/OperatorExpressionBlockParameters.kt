package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class OperatorExpressionBlockParameters(
    val leftOperand: ExpressionBlockData? = null,
    val rightOperand: ExpressionBlockData? = null
) : BlockParameters()
