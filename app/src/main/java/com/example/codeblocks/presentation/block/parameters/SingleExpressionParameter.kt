package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class SingleExpressionParameter(
    var expression: ExpressionBlockData? = null
) : BlockParameters()
