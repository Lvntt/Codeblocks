package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorType

class EqualityCheckBlock : OperatorBlock() {
    override val operatorType = OperatorType.EQUALITY
    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any {
        return firstValue == secondValue
    }
}