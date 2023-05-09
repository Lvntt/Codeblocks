package com.example.codeblocks.domain.entity.variables

import kotlin.reflect.KClass

object OperatorMap {

    val operators = mapOf(
        OperatorType.ADDITION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).toInt() + (second as Byte).toInt() },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toInt() + (second as Short).toInt() },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt() + (second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong() + (second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat() + (second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble() + (second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).toInt() + (second as Byte).toInt() },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).toInt() + (second as Short).toInt() },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt() + (second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong() + (second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat() + (second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble() + (second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int) + (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) + (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) + (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() + (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() + (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() + (second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long) + (second as Byte).toLong() },
            Pair(Long::class, Short::class) to { first, second -> (first as Long) + (second as Short).toLong() },
            Pair(Long::class, Int::class) to { first, second -> (first as Long) + (second as Int).toLong() },
            Pair(Long::class, Long::class) to { first, second -> (first as Long) + (second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat() + (second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble() + (second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float) + (second as Byte).toFloat() },
            Pair(Float::class, Short::class) to { first, second -> (first as Float) + (second as Short).toFloat() },
            Pair(Float::class, Int::class) to { first, second -> (first as Float) + (second as Int).toFloat() },
            Pair(Float::class, Long::class) to { first, second -> (first as Float) + (second as Long).toFloat() },
            Pair(Float::class, Float::class) to { first, second -> (first as Float) + (second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble() + (second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double) + (second as Byte).toDouble() },
            Pair(Double::class, Short::class) to { first, second -> (first as Double) + (second as Short).toDouble() },
            Pair(Double::class, Int::class) to { first, second -> (first as Double) + (second as Int).toDouble() },
            Pair(Double::class, Long::class) to { first, second -> (first as Double) + (second as Long).toDouble() },
            Pair(Double::class, Float::class) to { first, second -> (first as Double) + (second as Float).toDouble() },
            Pair(Double::class, Double::class) to { first, second -> (first as Double) + (second as Double) }
        ),
        OperatorType.SUBTRACTION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).toInt() - (second as Byte).toInt() },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toInt() - (second as Short).toInt() },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt() - (second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong() - (second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat() - (second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble() - (second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).toInt() - (second as Byte).toInt() },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).toInt() - (second as Short).toInt() },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt() - (second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong() - (second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat() - (second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble() - (second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int) - (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) - (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) - (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() - (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() - (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() - (second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long) - (second as Byte).toLong() },
            Pair(Long::class, Short::class) to { first, second -> (first as Long) - (second as Short).toLong() },
            Pair(Long::class, Int::class) to { first, second -> (first as Long) - (second as Int).toLong() },
            Pair(Long::class, Long::class) to { first, second -> (first as Long) - (second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat() - (second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble() - (second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float) - (second as Byte).toFloat() },
            Pair(Float::class, Short::class) to { first, second -> (first as Float) - (second as Short).toFloat() },
            Pair(Float::class, Int::class) to { first, second -> (first as Float) - (second as Int).toFloat() },
            Pair(Float::class, Long::class) to { first, second -> (first as Float) - (second as Long).toFloat() },
            Pair(Float::class, Float::class) to { first, second -> (first as Float) - (second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble() - (second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double) - (second as Byte).toDouble() },
            Pair(Double::class, Short::class) to { first, second -> (first as Double) - (second as Short).toDouble() },
            Pair(Double::class, Int::class) to { first, second -> (first as Double) - (second as Int).toDouble() },
            Pair(Double::class, Long::class) to { first, second -> (first as Double) - (second as Long).toDouble() },
            Pair(Double::class, Float::class) to { first, second -> (first as Double) - (second as Float).toDouble() },
            Pair(Double::class, Double::class) to { first, second -> (first as Double) - (second as Double) }
        ),
        OperatorType.MULTIPLICATION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).toInt() * (second as Byte).toInt() },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toInt() * (second as Short).toInt() },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt() * (second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong() * (second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat() * (second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble() * (second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).toInt() * (second as Byte).toInt() },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).toInt() * (second as Short).toInt() },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt() * (second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong() * (second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat() * (second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble() * (second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int) * (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) * (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) * (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() * (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() * (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() * (second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long) * (second as Byte).toLong() },
            Pair(Long::class, Short::class) to { first, second -> (first as Long) * (second as Short).toLong() },
            Pair(Long::class, Int::class) to { first, second -> (first as Long) * (second as Int).toLong() },
            Pair(Long::class, Long::class) to { first, second -> (first as Long) * (second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat() * (second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble() * (second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float) * (second as Byte).toFloat() },
            Pair(Float::class, Short::class) to { first, second -> (first as Float) * (second as Short).toFloat() },
            Pair(Float::class, Int::class) to { first, second -> (first as Float) * (second as Int).toFloat() },
            Pair(Float::class, Long::class) to { first, second -> (first as Float) * (second as Long).toFloat() },
            Pair(Float::class, Float::class) to { first, second -> (first as Float) * (second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble() * (second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double) * (second as Byte).toDouble() },
            Pair(Double::class, Short::class) to { first, second -> (first as Double) * (second as Short).toDouble() },
            Pair(Double::class, Int::class) to { first, second -> (first as Double) * (second as Int).toDouble() },
            Pair(Double::class, Long::class) to { first, second -> (first as Double) * (second as Long).toDouble() },
            Pair(Double::class, Float::class) to { first, second -> (first as Double) * (second as Float).toDouble() },
            Pair(Double::class, Double::class) to { first, second -> (first as Double) * (second as Double) }
        ),
        OperatorType.DIVISION to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).toInt() / (second as Byte).toInt() },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toInt() / (second as Short).toInt() },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt() / (second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong() / (second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat() / (second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble() / (second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).toInt() / (second as Byte).toInt() },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).toInt() / (second as Short).toInt() },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt() / (second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong() / (second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat() / (second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble() / (second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int) / (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) / (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) / (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() / (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() / (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() / (second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long) / (second as Byte).toLong() },
            Pair(Long::class, Short::class) to { first, second -> (first as Long) / (second as Short).toLong() },
            Pair(Long::class, Int::class) to { first, second -> (first as Long) / (second as Int).toLong() },
            Pair(Long::class, Long::class) to { first, second -> (first as Long) / (second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat() / (second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble() / (second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float) / (second as Byte).toFloat() },
            Pair(Float::class, Short::class) to { first, second -> (first as Float) / (second as Short).toFloat() },
            Pair(Float::class, Int::class) to { first, second -> (first as Float) / (second as Int).toFloat() },
            Pair(Float::class, Long::class) to { first, second -> (first as Float) / (second as Long).toFloat() },
            Pair(Float::class, Float::class) to { first, second -> (first as Float) / (second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble() / (second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double) / (second as Byte).toDouble() },
            Pair(Double::class, Short::class) to { first, second -> (first as Double) / (second as Short).toDouble() },
            Pair(Double::class, Int::class) to { first, second -> (first as Double) / (second as Int).toDouble() },
            Pair(Double::class, Long::class) to { first, second -> (first as Double) / (second as Long).toDouble() },
            Pair(Double::class, Float::class) to { first, second -> (first as Double) / (second as Float).toDouble() },
            Pair(Double::class, Double::class) to { first, second -> (first as Double) / (second as Double) }
        ),
        OperatorType.REMAINDER to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).toInt() % (second as Byte).toInt() },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toInt() % (second as Short).toInt() },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt() % (second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong() % (second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat() % (second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble() % (second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).toInt() % (second as Byte).toInt() },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).toInt() % (second as Short).toInt() },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt() % (second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong() % (second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat() % (second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble() % (second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int) % (second as Byte).toInt() },
            Pair(Int::class, Short::class) to { first, second -> (first as Int) % (second as Short).toInt() },
            Pair(Int::class, Int::class) to { first, second -> (first as Int) % (second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong() % (second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat() % (second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble() % (second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long) % (second as Byte).toLong() },
            Pair(Long::class, Short::class) to { first, second -> (first as Long) % (second as Short).toLong() },
            Pair(Long::class, Int::class) to { first, second -> (first as Long) % (second as Int).toLong() },
            Pair(Long::class, Long::class) to { first, second -> (first as Long) % (second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat() % (second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble() % (second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float) % (second as Byte).toFloat() },
            Pair(Float::class, Short::class) to { first, second -> (first as Float) % (second as Short).toFloat() },
            Pair(Float::class, Int::class) to { first, second -> (first as Float) % (second as Int).toFloat() },
            Pair(Float::class, Long::class) to { first, second -> (first as Float) % (second as Long).toFloat() },
            Pair(Float::class, Float::class) to { first, second -> (first as Float) % (second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble() % (second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double) % (second as Byte).toDouble() },
            Pair(Double::class, Short::class) to { first, second -> (first as Double) % (second as Short).toDouble() },
            Pair(Double::class, Int::class) to { first, second -> (first as Double) % (second as Int).toDouble() },
            Pair(Double::class, Long::class) to { first, second -> (first as Double) % (second as Long).toDouble() },
            Pair(Double::class, Float::class) to { first, second -> (first as Double) % (second as Float).toDouble() },
            Pair(Double::class, Double::class) to { first, second -> (first as Double) % (second as Double) }
        ),
        OperatorType.COMPARE_TO to mapOf<Pair<KClass<out Any>, KClass<out Any>>, (Any, Any) -> Any>(
            Pair(Byte::class, Byte::class) to { first, second -> (first as Byte).compareTo(second as Byte) },
            Pair(Byte::class, Short::class) to { first, second -> (first as Byte).toShort().compareTo(second as Short) },
            Pair(Byte::class, Int::class) to { first, second -> (first as Byte).toInt().compareTo(second as Int) },
            Pair(Byte::class, Long::class) to { first, second -> (first as Byte).toLong().compareTo(second as Long) },
            Pair(Byte::class, Float::class) to { first, second -> (first as Byte).toFloat().compareTo(second as Float) },
            Pair(Byte::class, Double::class) to { first, second -> (first as Byte).toDouble().compareTo(second as Double) },

            Pair(Short::class, Byte::class) to { first, second -> (first as Short).compareTo((second as Byte).toShort()) },
            Pair(Short::class, Short::class) to { first, second -> (first as Short).compareTo(second as Short) },
            Pair(Short::class, Int::class) to { first, second -> (first as Short).toInt().compareTo(second as Int) },
            Pair(Short::class, Long::class) to { first, second -> (first as Short).toLong().compareTo(second as Long) },
            Pair(Short::class, Float::class) to { first, second -> (first as Short).toFloat().compareTo(second as Float) },
            Pair(Short::class, Double::class) to { first, second -> (first as Short).toDouble().compareTo(second as Double) },

            Pair(Int::class, Byte::class) to { first, second -> (first as Int).compareTo((second as Byte).toInt()) },
            Pair(Int::class, Short::class) to { first, second -> (first as Int).compareTo((second as Short).toInt()) },
            Pair(Int::class, Int::class) to { first, second -> (first as Int).compareTo(second as Int) },
            Pair(Int::class, Long::class) to { first, second -> (first as Int).toLong().compareTo(second as Long) },
            Pair(Int::class, Float::class) to { first, second -> (first as Int).toFloat().compareTo(second as Float) },
            Pair(Int::class, Double::class) to { first, second -> (first as Int).toDouble().compareTo(second as Double) },

            Pair(Long::class, Byte::class) to { first, second -> (first as Long).compareTo((second as Byte).toLong()) },
            Pair(Long::class, Short::class) to { first, second -> (first as Long).compareTo((second as Short).toLong()) },
            Pair(Long::class, Int::class) to { first, second -> (first as Long).compareTo((second as Int).toLong()) },
            Pair(Long::class, Long::class) to { first, second -> (first as Long).compareTo(second as Long) },
            Pair(Long::class, Float::class) to { first, second -> (first as Long).toFloat().compareTo(second as Float) },
            Pair(Long::class, Double::class) to { first, second -> (first as Long).toDouble().compareTo(second as Double) },

            Pair(Float::class, Byte::class) to { first, second -> (first as Float).compareTo((second as Byte).toFloat()) },
            Pair(Float::class, Short::class) to { first, second -> (first as Float).compareTo((second as Short).toFloat()) },
            Pair(Float::class, Int::class) to { first, second -> (first as Float).compareTo((second as Int).toFloat()) },
            Pair(Float::class, Long::class) to { first, second -> (first as Float).compareTo((second as Long).toFloat()) },
            Pair(Float::class, Float::class) to { first, second -> (first as Float).compareTo(second as Float) },
            Pair(Float::class, Double::class) to { first, second -> (first as Float).toDouble().compareTo(second as Double) },

            Pair(Double::class, Byte::class) to { first, second -> (first as Double).compareTo((second as Byte).toDouble()) },
            Pair(Double::class, Short::class) to { first, second -> (first as Double).compareTo((second as Short).toDouble()) },
            Pair(Double::class, Int::class) to { first, second -> (first as Double).compareTo((second as Int).toDouble()) },
            Pair(Double::class, Long::class) to { first, second -> (first as Double).compareTo((second as Long).toDouble()) },
            Pair(Double::class, Float::class) to { first, second -> (first as Double).compareTo((second as Float).toDouble()) },
            Pair(Double::class, Double::class) to { first, second -> (first as Double).compareTo(second as Double) }
        )
    )

}