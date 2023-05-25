package com.example.codeblocks.domain.entity.blocks.expression.operators.logic

import com.example.codeblocks.domain.entity.blocks.expression.operators.OperatorBlock
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import com.example.codeblocks.domain.entity.variables.OperatorType
import com.example.codeblocks.domain.entity.variables.VariableCasts
import com.example.codeblocks.domain.entity.Variable

class AndBlock : OperatorBlock() {

    override val operatorType = OperatorType.CONJUNCTION

    override fun getOperatorResultValue(firstVariable: Variable, secondVariable: Variable): Any {
        if (!((firstVariable is BooleanVariable) && (secondVariable is BooleanVariable))) { /*TODO error handling*/ throw Exception() }

        if (!VariableCasts.typeCanBeSeamlesslyConverted(firstVariable, BooleanVariable::class)) { /*TODO error handling*/ throw Exception() }
        val firstReturnedValue = VariableCasts.castVariable(firstVariable, BooleanVariable::class) ?: /*TODO error handling*/ throw Exception()
        val firstBooleanValue = (firstReturnedValue as BooleanVariable).getValue() ?: /*TODO error handling*/ throw Exception()

        if (!VariableCasts.typeCanBeSeamlesslyConverted(secondVariable, BooleanVariable::class)) { /*TODO error handling*/ throw Exception() }
        val secondReturnedValue = VariableCasts.castVariable(secondVariable, BooleanVariable::class) ?: /*TODO error handling*/ throw Exception()
        val secondBooleanValue = (secondReturnedValue as BooleanVariable).getValue() ?: /*TODO error handling*/ throw Exception()

        return (firstBooleanValue && secondBooleanValue)
    }

}