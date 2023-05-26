package com.example.codeblocks.domain.entity.blocks.variable.list

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.ListVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts.castVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts.typeCanBeSeamlesslyConverted
import kotlin.reflect.KClass

class AddElementToListBlock : Block() {

    override val paramType: KClass<out ParamBundle> = TwoExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as TwoExpressionBlockBundle).firstExpressionBlock.setupScope(scope)
        val variableToAdd = (paramBundle as TwoExpressionBlockBundle).firstExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        (paramBundle as TwoExpressionBlockBundle).secondExpressionBlock.setupScope(scope)
        val list = (paramBundle as TwoExpressionBlockBundle).secondExpressionBlock.getReturnedValue()
            ?: /*TODO error handling*/ throw Exception()

        if (list is ListVariable && typeCanBeSeamlesslyConverted(variableToAdd, list.elementType)) {
            list.addElement(castVariable(variableToAdd, list.elementType)!!)
        } else {
            //TODO error handling
            throw Exception()
        }
    }

}