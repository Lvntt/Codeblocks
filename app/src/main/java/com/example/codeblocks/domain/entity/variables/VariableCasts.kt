package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

object VariableCasts {
    val castMap: Map<Pair<KClass<out Variable>, KClass<out Variable>>, (Variable) -> Variable> =
        mapOf(
            Pair(ByteVariable::class, ByteVariable::class) to { it },
            Pair(ByteVariable::class, ShortVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(value?.toShort())
                castedVariable
            },
            Pair(ByteVariable::class, IntegerVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(value?.toInt())
                castedVariable
            },
            Pair(ByteVariable::class, LongVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(value?.toLong())
                castedVariable
            },
            Pair(ByteVariable::class, FloatVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(value?.toFloat())
                castedVariable
            },
            Pair(ByteVariable::class, DoubleVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(value?.toDouble())
                castedVariable
            },
            Pair(ByteVariable::class, BooleanVariable::class) to {
                val value = (it as ByteVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toInt() != 0)
                castedVariable
            },
            Pair(ShortVariable::class, ByteVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(value?.toByte())
                castedVariable
            },
            Pair(ShortVariable::class, ShortVariable::class) to { it },
            Pair(ShortVariable::class, IntegerVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(value?.toInt())
                castedVariable
            },
            Pair(ShortVariable::class, LongVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(value?.toLong())
                castedVariable
            },
            Pair(ShortVariable::class, FloatVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(value?.toFloat())
                castedVariable
            },
            Pair(ShortVariable::class, DoubleVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(value?.toDouble())
                castedVariable
            },
            Pair(ShortVariable::class, BooleanVariable::class) to {
                val value = (it as ShortVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toInt() != 0)
                castedVariable
            },
            Pair(IntegerVariable::class, ByteVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(value?.toByte())
                castedVariable
            },
            Pair(IntegerVariable::class, ShortVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(value?.toShort())
                castedVariable
            },
            Pair(IntegerVariable::class, IntegerVariable::class) to { it },
            Pair(IntegerVariable::class, LongVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(value?.toLong())
                castedVariable
            },
            Pair(IntegerVariable::class, FloatVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(value?.toFloat())
                castedVariable
            },
            Pair(IntegerVariable::class, DoubleVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(value?.toDouble())
                castedVariable
            },
            Pair(IntegerVariable::class, BooleanVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toInt() != 0)
                castedVariable
            },
            Pair(LongVariable::class, ByteVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(value?.toByte())
                castedVariable
            },
            Pair(LongVariable::class, ShortVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(value?.toShort())
                castedVariable
            },
            Pair(LongVariable::class, IntegerVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(value?.toInt())
                castedVariable
            },
            Pair(LongVariable::class, LongVariable::class) to { it },
            Pair(LongVariable::class, FloatVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(value?.toFloat())
                castedVariable
            },
            Pair(LongVariable::class, DoubleVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(value?.toDouble())
                castedVariable
            },
            Pair(LongVariable::class, BooleanVariable::class) to {
                val value = (it as LongVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toInt() != 0)
                castedVariable
            },
            Pair(FloatVariable::class, ByteVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(value?.toInt()?.toByte())
                castedVariable
            },
            Pair(FloatVariable::class, ShortVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(value?.toInt()?.toShort())
                castedVariable
            },
            Pair(FloatVariable::class, IntegerVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(value?.toInt())
                castedVariable
            },
            Pair(FloatVariable::class, LongVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(value?.toLong())
                castedVariable
            },
            Pair(FloatVariable::class, FloatVariable::class) to { it },
            Pair(FloatVariable::class, DoubleVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(value?.toDouble())
                castedVariable
            },
            Pair(FloatVariable::class, BooleanVariable::class) to {
                val value = (it as FloatVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toDouble() != 0.0)
                castedVariable
            },
            Pair(DoubleVariable::class, ByteVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(value?.toInt()?.toByte())
                castedVariable
            },
            Pair(DoubleVariable::class, ShortVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(value?.toInt()?.toShort())
                castedVariable
            },
            Pair(DoubleVariable::class, IntegerVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(value?.toInt())
                castedVariable
            },
            Pair(DoubleVariable::class, LongVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(value?.toLong())
                castedVariable
            },
            Pair(DoubleVariable::class, FloatVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(value?.toFloat())
                castedVariable
            },
            Pair(DoubleVariable::class, DoubleVariable::class) to { it },
            Pair(DoubleVariable::class, BooleanVariable::class) to {
                val value = (it as DoubleVariable).getValue()
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(value != null && value.toDouble() != 0.0)
                castedVariable
            },
            Pair(BooleanVariable::class, ByteVariable::class) to {
                val value = (it as BooleanVariable).getValue()
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(
                    if (value == null) null else {
                        if (value) 1 else 0
                    }
                )
                castedVariable
            },
            Pair(BooleanVariable::class, ShortVariable::class) to {
                val value = (it as BooleanVariable).getValue()
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(
                    if (value == null) null else {
                        if (value) 1 else 0
                    }
                )
                castedVariable
            },
            Pair(BooleanVariable::class, IntegerVariable::class) to {
                val value = (it as BooleanVariable).getValue()
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(
                    if (value == null) null else {
                        if (value) 1 else 0
                    }
                )
                castedVariable
            },
            Pair(BooleanVariable::class, FloatVariable::class) to {
                val value = (it as BooleanVariable).getValue()
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(
                    if (value == null) null else {
                        if (value) 1 else 0
                    }
                )
                castedVariable
            },
            Pair(BooleanVariable::class, ByteVariable::class) to {
                val value = (it as BooleanVariable).getValue()
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(
                    if (value == null) null else {
                        if (value) 1 else 0
                    }
                )
                castedVariable
            },
            Pair(BooleanVariable::class, BooleanVariable::class) to { it },
            Pair(NullVariable::class, ByteVariable::class) to {
                val castedVariable = ByteVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, ShortVariable::class) to {
                val castedVariable = ShortVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, IntegerVariable::class) to {
                val castedVariable = IntegerVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, LongVariable::class) to {
                val castedVariable = LongVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, FloatVariable::class) to {
                val castedVariable = FloatVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, DoubleVariable::class) to {
                val castedVariable = DoubleVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, BooleanVariable::class) to {
                val castedVariable = BooleanVariable(it.name)
                castedVariable.setValue(null)
                castedVariable
            },
            Pair(NullVariable::class, NullVariable::class) to { it }
        )
    private val typeCompatibilityMap: Map<Pair<KClass<out Variable>, KClass<out Variable>>, (Variable) -> Boolean> =
        mapOf(
            Pair(ByteVariable::class, ByteVariable::class) to { true },
            Pair(ByteVariable::class, ShortVariable::class) to { true },
            Pair(ByteVariable::class, IntegerVariable::class) to { true },
            Pair(ByteVariable::class, LongVariable::class) to { true },
            Pair(ByteVariable::class, BooleanVariable::class) to { true },
            Pair(ShortVariable::class, ByteVariable::class) to {
                val value = (it as ShortVariable).getValue()
                value == null || value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE
            },
            Pair(ShortVariable::class, ShortVariable::class) to { true },
            Pair(ShortVariable::class, IntegerVariable::class) to { true },
            Pair(ShortVariable::class, LongVariable::class) to { true },
            Pair(ShortVariable::class, BooleanVariable::class) to { true },
            Pair(IntegerVariable::class, ByteVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                value == null || value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE
            },
            Pair(IntegerVariable::class, ShortVariable::class) to {
                val value = (it as IntegerVariable).getValue()
                value == null || value <= Short.MAX_VALUE && value >= Short.MIN_VALUE
            },
            Pair(IntegerVariable::class, IntegerVariable::class) to { true },
            Pair(IntegerVariable::class, LongVariable::class) to { true },
            Pair(IntegerVariable::class, BooleanVariable::class) to { true },
            Pair(LongVariable::class, ByteVariable::class) to {
                val value = (it as LongVariable).getValue()
                value == null || value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE
            },
            Pair(LongVariable::class, ShortVariable::class) to {
                val value = (it as LongVariable).getValue()
                value == null || value <= Short.MAX_VALUE && value >= Short.MIN_VALUE
            },
            Pair(LongVariable::class, IntegerVariable::class) to {
                val value = (it as LongVariable).getValue()
                value == null || value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE
            },
            Pair(LongVariable::class, LongVariable::class) to { true },
            Pair(LongVariable::class, BooleanVariable::class) to { true },
            Pair(FloatVariable::class, FloatVariable::class) to { true },
            Pair(DoubleVariable::class, DoubleVariable::class) to { true },
            Pair(BooleanVariable::class, BooleanVariable::class) to { true },
            Pair(NullVariable::class, ByteVariable::class) to { true },
            Pair(NullVariable::class, ShortVariable::class) to { true },
            Pair(NullVariable::class, IntegerVariable::class) to { true },
            Pair(NullVariable::class, LongVariable::class) to { true },
            Pair(NullVariable::class, FloatVariable::class) to { true },
            Pair(NullVariable::class, DoubleVariable::class) to { true },
            Pair(NullVariable::class, BooleanVariable::class) to { true },
            Pair(NullVariable::class, NullVariable::class) to { true }
        )

    @JvmStatic
    fun typeCanBeSeamlesslyConverted(
        firstTypeVariable: Variable,
        secondType: KClass<out Variable>
    ): Boolean {
        val compatibilityLambda = typeCompatibilityMap[Pair(firstTypeVariable::class, secondType)]
            ?: return false
        return compatibilityLambda(firstTypeVariable)
    }

    @JvmStatic
    fun castVariable(variable: Variable, to: KClass<out Variable>): Variable? {
        val castLambda = castMap[Pair(variable::class, to)]
        if (castLambda != null)
            return castLambda(variable)
        return null
    }
}