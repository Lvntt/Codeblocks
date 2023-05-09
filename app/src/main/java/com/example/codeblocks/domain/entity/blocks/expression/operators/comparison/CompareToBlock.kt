package com.example.codeblocks.domain.entity.blocks.expression.operators.comparison

import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MathOperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorType

class CompareToBlock : MathOperatorBlock() {

    override val operatorType = OperatorType.COMPARE_TO

}