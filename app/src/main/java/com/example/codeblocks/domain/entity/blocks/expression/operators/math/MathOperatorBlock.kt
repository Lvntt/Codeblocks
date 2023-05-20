package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorMap
import com.example.codeblocks.domain.entity.Variable

abstract class MathOperatorBlock : OperatorBlock() {
    override fun getOperatorResultValue(firstVariable: Variable, secondVariable: Variable): Any? {
        val firstValue = firstVariable.getValue()
        val secondValue = secondVariable.getValue()
        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val operatorAction = OperatorMap.operators[operatorType]?.get(Pair(firstValue::class, secondValue::class))
            ?: /*TODO error handling*/ throw Exception()
        return operatorAction(firstValue, secondValue)
    }

}