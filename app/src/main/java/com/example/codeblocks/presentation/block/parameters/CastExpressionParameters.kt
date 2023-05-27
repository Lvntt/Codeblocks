package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.CastExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import kotlin.reflect.KClass

data class CastExpressionParameters(
    var expressionToCast: ExpressionBlockData? = null,
    var castTo: KClass<out Variable> = IntegerVariable::class,
    override val paramType: KClass<out BlockParameters> = CastExpressionParameters::class
) : BlockParameters() {

    override fun createBundle(): ParamBundle {
        return CastExpressionBlockBundle(
            expressionToCast?.createBlock() as ExpressionBlock?
                ?: /*TODO error handling*/ throw Exception(),
            castTo
        )
    }

}
