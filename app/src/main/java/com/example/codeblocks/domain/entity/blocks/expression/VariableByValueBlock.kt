package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.parambundles.expression.VariableValueBundle
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.domain.entity.variables.NullVariable
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class VariableByValueBlock: ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = VariableValueBundle::class

    override fun executeAfterChecks() {
        val value = convertStringToVariable((paramBundle as VariableValueBundle).value)
        if(value != null) {
            val variable = typeMap[value::class]?.primaryConstructor?.call("")
            if (variable != null) {
                val setValueCallable = variable::class.members.single { it.name == "setValue" }
                setValueCallable.call(variable, value)
                returnedVariable = variable
            } else {
                //TODO error handling
            }
        } else {
            returnedVariable = NullVariable("")
        }
    }

    companion object {
        private val typeMap = mapOf<KClass<out Any>, KClass<out Variable>>(
            Int::class to IntegerVariable::class,
            Boolean::class to BooleanVariable::class
        )

        @JvmStatic
        private fun convertStringToVariable(string: String): Any? {
            val integerRegex = Regex("^-?[0-9]+$")
            val booleanRegex = Regex("^(?:true|false)$")
            if(string.matches(integerRegex)) {
                return string.toInt()
            }
            if(string.matches(booleanRegex)) {
                return string.toBooleanStrict()
            }
            return null
        }
    }
}