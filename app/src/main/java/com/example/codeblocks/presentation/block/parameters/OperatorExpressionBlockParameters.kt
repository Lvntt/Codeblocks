package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class OperatorExpressionBlockParameters(
    var leftOperand: ExpressionBlockData? = null,
    var rightOperand: ExpressionBlockData? = null
) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        val leftOperand = leftOperand
        val rightOperand = rightOperand

        if (leftOperand == null || rightOperand == null) {
            // TODO catch in vm
            throw Exception()
        }
        return TwoExpressionBlockBundle(
            leftOperand.createBlock() as ExpressionBlock,
            rightOperand.createBlock() as ExpressionBlock
        )
    }

}