package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.variable.SetVariableBundle
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import kotlin.reflect.KClass

data class VariableAssignmentBlockParameters(
    var name: String = DefaultValues.EMPTY_STRING,
    var expression: ExpressionBlockData? = null,
    override val paramType: KClass<out BlockParameters> = VariableAssignmentBlockParameters::class
) : BlockParameters() {

    override fun createBundle(): ParamBundle {
        val expression = expression
            ?: // TODO catch in vm
            throw Exception()
        return SetVariableBundle(
            name,
            expression.createBlock() as ExpressionBlock
        )
    }

}
