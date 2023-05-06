package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorMap

abstract class MathOperatorBlock : OperatorBlock() {
    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any? {
        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val operatorAction = OperatorMap.operators[operatorType]?.get(Pair(firstValue::class, secondValue::class))
            ?: /*TODO error handling*/ throw Exception()
        return operatorAction(firstValue, secondValue)
    }

}