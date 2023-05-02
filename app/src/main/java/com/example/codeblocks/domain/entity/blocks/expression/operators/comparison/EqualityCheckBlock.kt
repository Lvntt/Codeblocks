package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock

class EqualityCheckBlock : OperatorBlock() {
    override val kClassOperatorName: String = "equalsOperator"
    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any {
        return firstValue == secondValue
    }
}