package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.VariableValueBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.convertStringToVariableValue
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.typeMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class VariableByValueBlock : ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = VariableValueBundle::class

    override fun executeAfterChecks(scope: Scope) {
        val value = convertStringToVariableValue((paramBundle as VariableValueBundle).value)
        returnedVariable = if (value != null) {
            val variable = typeMap[value::class]?.primaryConstructor?.call("") ?: /*TODO error handling*/ throw Exception()
            variable.setValue(value)
            variable
        } else {
            NullVariable("")
        }
    }
}