package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorExtensions

abstract class MathOperatorBlock : OperatorBlock() {
    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any? {
        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val operatorLambda = OperatorExtensions.operators[kClassOperatorName]?.get(Pair(firstValue::class, secondValue::class))
            ?: /*TODO error handling*/ throw Exception()
        return operatorLambda(firstValue, secondValue)
    }
}