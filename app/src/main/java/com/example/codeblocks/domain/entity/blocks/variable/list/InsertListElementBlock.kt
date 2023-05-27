package com.example.codeblocks.domain.entity.blocks.variable.list

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.ThreeExpressionBundle
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.domain.entity.variables.ListVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts
import kotlin.reflect.KClass

class InsertListElementBlock: Block() {

    override val paramType: KClass<out ParamBundle> = ThreeExpressionBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as ThreeExpressionBundle).secondExpressionBlock.setupScope(scope)
        val index = (paramBundle as ThreeExpressionBundle).secondExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        (paramBundle as ThreeExpressionBundle).thirdExpressionBlock.setupScope(scope)
        val list = (paramBundle as ThreeExpressionBundle).thirdExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        (paramBundle as ThreeExpressionBundle).firstExpressionBlock.setupScope(scope)
        val variableToInsert = (paramBundle as ThreeExpressionBundle).firstExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        if (list is ListVariable && VariableCasts.typeCanBeSeamlesslyConverted(variableToInsert, list.elementType)) {
            val indexValue = VariableCasts.castVariable(index, IntegerVariable::class)?.getValue()
                ?: /*TODO error handling*/ throw Exception()
            list.insertElement(VariableCasts.castVariable(variableToInsert, list.elementType)!!, indexValue as Int)
        } else {
            //TODO error handling
            throw Exception()
        }

    }

}