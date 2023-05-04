package com.example.codeblocks.domain.entity.blocks.expression.operators

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.OperatorType
import com.example.codeblocks.domain.entity.variables.VariableTypeMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

abstract class OperatorBlock: ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = TwoExpressionBlockBundle::class
    protected abstract val operatorType: OperatorType

    override fun executeAfterChecks(scope: Scope) {
        val firstVariable =
            getVariableFromParams((paramBundle as TwoExpressionBlockBundle).firstExpressionBlock, scope)
        val secondVariable =
            getVariableFromParams((paramBundle as TwoExpressionBlockBundle).secondExpressionBlock, scope)

        if (firstVariable == null || secondVariable == null) { /*TODO error handling*/ throw Exception() }
        val firstValue = firstVariable.getValue()
        val secondValue = secondVariable.getValue()

        val resultValue = getOperatorResultValue(firstValue, secondValue)
        if (resultValue == null || VariableTypeMap.typeMap[resultValue::class] == null) { /*TODO error handling*/ throw Exception() }
        val variableToReturn = VariableTypeMap.typeMap[resultValue::class]?.primaryConstructor?.call("")
                ?: /*TODO error handling*/ throw Exception()
        variableToReturn.setValue(resultValue)
        returnedVariable = variableToReturn
    }

    protected abstract fun getOperatorResultValue(firstValue: Any?, secondValue: Any?): Any?
}