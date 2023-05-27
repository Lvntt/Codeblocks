package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.domain.entity.variables.ListVariable
import kotlin.reflect.KClass

class ListSizeBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {

        (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
        val list = (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        if (list is ListVariable) {
            val result = IntegerVariable(DefaultValues.EMPTY_STRING)
            result.setValue(list.getSize())
            returnedVariable = result
        } else {
            //TODO error handling
            throw Exception()
        }
    }

}