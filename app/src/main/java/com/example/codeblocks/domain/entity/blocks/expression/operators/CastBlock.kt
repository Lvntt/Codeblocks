package com.example.codeblocks.domain.entity.blocks.expression.operators

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.CastExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.VariableCasts.castVariable
import kotlin.reflect.KClass

class CastBlock: ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = CastExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val variable =
            getVariableFromParams((paramBundle as CastExpressionBlockBundle).expressionBlock, scope) ?: /*TODO error handling*/ throw Exception()
        returnedVariable = castVariable(variable, (paramBundle as CastExpressionBlockBundle).castTo) ?: /* TODO error handling*/ throw Exception()
    }

}