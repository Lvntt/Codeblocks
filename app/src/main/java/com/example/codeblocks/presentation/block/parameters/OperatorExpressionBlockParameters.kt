package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class OperatorExpressionBlockParameters(
    var leftOperand: ExpressionBlockData? = null,
    var rightOperand: ExpressionBlockData? = null
) : BlockParameters()
