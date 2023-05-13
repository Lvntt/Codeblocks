package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class VariableAssignmentBlockParameters(
    var name: String = "",
    var expression: ExpressionBlockData? = null
) : BlockParameters()
