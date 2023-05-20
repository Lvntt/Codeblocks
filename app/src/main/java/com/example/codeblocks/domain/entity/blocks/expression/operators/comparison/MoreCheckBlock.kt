package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorMap
import com.example.codeblocks.domain.entity.variables.OperatorType
import com.example.codeblocks.domain.entity.Variable

class MoreCheckBlock : OperatorBlock() {

    override val operatorType = OperatorType.MORE

    override fun getOperatorResultValue(firstVariable: Variable, secondVariable: Variable): Any {

        val firstValue = firstVariable.getValue()
        val secondValue = secondVariable.getValue()
        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val operatorAction = OperatorMap.operators[OperatorType.COMPARE_TO]?.get(Pair(firstValue::class, secondValue::class))
            ?: /*TODO error handling*/ throw Exception()
        return (operatorAction(firstValue, secondValue) as Int) > 0

    }

}