package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

object VariableTypeMap {
    private val integerRegex = Regex("^-?[0-9]+$")
    private val booleanRegex = Regex("^(?:true|false)$")

    val typeMap = mapOf<KClass<out Any>, KClass<out Variable>>(
        Int::class to IntegerVariable::class,
        Boolean::class to BooleanVariable::class
    )

    @JvmStatic
    fun convertStringToVariableValue(string: String): Any? {

        if (string.matches(integerRegex)) {
            return string.toInt()
        }
        if (string.matches(booleanRegex)) {
            return string.toBooleanStrict()
        }
        return null
    }

}