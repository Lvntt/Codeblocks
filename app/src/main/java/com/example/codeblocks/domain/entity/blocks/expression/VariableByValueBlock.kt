package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.convertStringToPrimitiveValue
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.typeMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class VariableByValueBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = VariableBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val value = convertStringToPrimitiveValue((paramBundle as VariableBundle).value)
        returnedVariable = if (value != null) {
            val variable = typeMap[value::class]?.primaryConstructor?.call(DefaultValues.EMPTY_STRING)
                ?: /*TODO error handling*/ throw Exception()
            variable.setValue(value)
            variable
        } else {
            NullVariable(DefaultValues.EMPTY_STRING)
        }
    }

}