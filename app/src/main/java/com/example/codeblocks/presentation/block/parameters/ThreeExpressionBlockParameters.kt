package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.ThreeExpressionBundle
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class ThreeExpressionBlockParameters(
    var firstExpression: ExpressionBlockData? = null,
    var secondExpression: ExpressionBlockData? = null,
    var thirdExpression: ExpressionBlockData? = null,
) : BlockParameters() {

    override fun createBundle(): ParamBundle {
        val firstExpression = firstExpression
        val secondExpression = secondExpression
        val thirdExpression = thirdExpression

        if (firstExpression == null || secondExpression == null || thirdExpression == null) {
            // TODO catch in vm
            throw Exception()
        }
        return ThreeExpressionBundle(
            firstExpression.createBlock() as ExpressionBlock,
            secondExpression.createBlock() as ExpressionBlock,
            thirdExpression.createBlock() as ExpressionBlock
        )
    }

}
