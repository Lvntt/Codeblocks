package com.example.codeblocks.domain.entity.variables

import kotlin.reflect.KClass

object OperatorMap {
    val operators = mapOf(
        OperatorType.ADDITION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Int::class, Byte::class) to { first, second -> (first as Int) + (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) + (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) + (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() + (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() + (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() + (second as Double) },
        ),
        OperatorType.SUBTRACTION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Int::class, Byte::class) to { first, second -> (first as Int) - (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) - (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) - (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() - (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() - (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() - (second as Double) },
        ),
        OperatorType.MULTIPLICATION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Int::class, Byte::class) to { first, second -> (first as Int) * (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) * (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) * (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() * (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() * (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() * (second as Double) },
        ),
        OperatorType.DIVISION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Int::class, Byte::class) to { first, second -> (first as Int) / (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) / (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) / (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() / (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() / (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() / (second as Double) },
        ),
        OperatorType.REMAINDER to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Int::class, Byte::class) to { first, second -> (first as Int) % (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) % (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) % (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() % (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() % (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() % (second as Double) },
        )
    )
}