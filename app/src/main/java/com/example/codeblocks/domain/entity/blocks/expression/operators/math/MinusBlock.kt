package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.variables.OperatorType

class MinusBlock: MathOperatorBlock() {
    override val operatorType = OperatorType.SUBTRACTION
}