package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorMap
import com.example.codeblocks.domain.entity.variables.OperatorType


class LessCheckBlock : OperatorBlock() {

    override val operatorType = OperatorType.LESS

    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any {

        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val operatorAction = OperatorMap.operators[OperatorType.COMPARE_TO]?.get(Pair(firstValue::class, secondValue::class))
            ?: /*TODO error handling*/ throw Exception()
        return (operatorAction(firstValue, secondValue) as Int) < 0

    }

}