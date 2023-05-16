package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorMap
import com.example.codeblocks.domain.entity.variables.OperatorType

class NotEqualCheckBlock : OperatorBlock() {

    override val operatorType = OperatorType.NOT_EQUAL

    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any {
        if (firstValue != null && secondValue != null) {
            val operatorAction = OperatorMap.operators[OperatorType.COMPARE_TO]?.get(Pair(firstValue::class, secondValue::class)) ?: return firstValue != secondValue
            return (operatorAction(firstValue, secondValue) as Int) != 0
        }
        return firstValue != secondValue
    }

}