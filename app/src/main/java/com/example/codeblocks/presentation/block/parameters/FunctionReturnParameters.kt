package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.function.FunctionReturnBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class FunctionReturnParameters(
    var expression: ExpressionBlockData? = null
) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        return FunctionReturnBundle(
            expression?.createBlock() as ExpressionBlock?
        )
    }

}