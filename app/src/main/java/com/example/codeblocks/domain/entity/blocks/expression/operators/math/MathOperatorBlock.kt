package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.OperatorExtensions
import kotlin.reflect.full.createType

abstract class MathOperatorBlock : OperatorBlock() {
    override fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any? {
        if (firstValue == null || secondValue == null) { /*TODO error handling*/ throw Exception() }
        val availableOperators = OperatorExtensions::class.members.filter { it.name == kClassOperatorName && it.parameters[1].type == firstValue::class.createType() && it.parameters[2].type == secondValue::class.createType()}
        if(availableOperators.size != 1) { /*TODO error handling*/ throw Exception() }
        return availableOperators[0].call(OperatorExtensions, firstValue, secondValue)
    }
}