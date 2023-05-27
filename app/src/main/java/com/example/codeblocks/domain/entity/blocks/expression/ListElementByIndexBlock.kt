package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.domain.entity.variables.ListVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts
import kotlin.reflect.KClass

class ListElementByIndexBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = TwoExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as TwoExpressionBlockBundle).firstExpressionBlock.setupScope(scope)
        val index = (paramBundle as TwoExpressionBlockBundle).firstExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        (paramBundle as TwoExpressionBlockBundle).secondExpressionBlock.setupScope(scope)
        val list = (paramBundle as TwoExpressionBlockBundle).secondExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        if (list is ListVariable && VariableCasts.typeCanBeSeamlesslyConverted(index, IntegerVariable::class)) {
            val indexValue = VariableCasts.castVariable(index, IntegerVariable::class)?.getValue()
                ?: /*TODO error handling*/ throw Exception()
            returnedVariable = list.getElement(indexValue as Int)
        } else {
            //TODO error handling
            throw Exception()
        }
    }

}