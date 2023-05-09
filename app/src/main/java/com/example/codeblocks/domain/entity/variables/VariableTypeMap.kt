package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

object VariableTypeMap {
    private val floatRegex = Regex("^-?[0-9]+[.][0-9]+[fF]$")
    private val doubleRegex = Regex("^-?[0-9]+[.][0-9]+$")
    private val integerRegex = Regex("^-?[0-9]+$")
    private val booleanRegex = Regex("^(?:true|false)$")

    val typeMap = mapOf<KClass<out Any>, KClass<out Variable>>(
        Byte::class to ByteVariable::class,
        Short::class to ShortVariable::class,
        Int::class to IntegerVariable::class,
        Long::class to LongVariable::class,
        Float::class to FloatVariable::class,
        Double::class to DoubleVariable::class,
        Boolean::class to BooleanVariable::class
    )

    @JvmStatic
    fun convertStringToVariableValue(string: String): Any? {

        if (string.matches(floatRegex)){
            return string.toFloat()
        }
        if (string.matches(doubleRegex)){
            return string.toDouble()
        }
        if (string.matches(integerRegex) && (string.toLong() <= 127) && (string.toLong() >= -128)){
            return string.toByte()
        }
        if (string.matches(integerRegex) && (string.toLong() <= 32767) && (string.toLong() >= -32768)){
            return string.toShort()
        }
        if (string.matches(integerRegex) && (string.toLong() <= 2147483647) && (string.toLong() >= -2147483648)) {
            return string.toInt()
        }
        if (string.matches(integerRegex)){
            return  string.toLong()
        }
        if (string.matches(booleanRegex)) {
            return string.toBooleanStrict()
        }
        return null
    }

}