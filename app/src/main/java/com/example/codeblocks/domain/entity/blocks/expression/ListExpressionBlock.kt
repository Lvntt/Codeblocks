package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.ListExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.ListVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts
import kotlin.reflect.KClass

class ListExpressionBlock: ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = ListExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val list =
            ListVariable(DefaultValues.EMPTY_STRING, (paramBundle as ListExpressionBlockBundle).type)
        (paramBundle as ListExpressionBlockBundle).elements.forEach {
            it.setupScope(scope)
            val returnedResult = it.getReturnedValue() ?: /*TODO error handling*/ throw Exception()
            if (VariableCasts.typeCanBeSeamlesslyConverted(returnedResult, list.elementType)) {
                list.addElement(VariableCasts.castVariable(returnedResult, list.elementType)!!)
            } else {
                /*TODO error handling*/ throw Exception()
            }
        }
        returnedVariable = list
    }
}