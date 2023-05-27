package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import kotlin.reflect.KClass

data class SingleExpressionParameter(
    var expression: ExpressionBlockData? = null,
    override val paramType: KClass<out BlockParameters> = SingleExpressionParameter::class
) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        val expression = expression
            ?: // TODO catch in vm
            throw Exception()
        return SingleExpressionBlockBundle(
            expression.createBlock() as ExpressionBlock
        )
    }

}
