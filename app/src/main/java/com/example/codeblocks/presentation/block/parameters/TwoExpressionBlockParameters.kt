package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class TwoExpressionBlockParameters(
    var firstExpression: ExpressionBlockData? = null,
    var secondExpression: ExpressionBlockData? = null
) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        val firstExpression = firstExpression
        val secondExpression = secondExpression

        if (firstExpression == null || secondExpression == null) {
            // TODO catch in vm
            throw Exception()
        }
        return TwoExpressionBlockBundle(
            firstExpression.createBlock() as ExpressionBlock,
            secondExpression.createBlock() as ExpressionBlock
        )
    }

}