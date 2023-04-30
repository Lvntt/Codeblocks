package com.example.codeblocks.domain.entity.blocks.expression.operators.math

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.typeMap
import kotlin.reflect.KClass
import kotlin.reflect.full.createType
import kotlin.reflect.full.primaryConstructor

abstract class MathOperatorBlock : ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = TwoExpressionBlockBundle::class
    abstract val kClassOperatorName: String

    override fun executeAfterChecks(scope: Scope) {
        val firstVariable =
            getVariableFromParams((paramBundle as TwoExpressionBlockBundle).firstExpressionBlock, scope)
        val secondVariable =
            getVariableFromParams((paramBundle as TwoExpressionBlockBundle).secondExpressionBlock, scope)
        if (firstVariable != null && secondVariable != null) {
            val firstValueCallable = firstVariable::class.members.single { it.name == "getValue" }
            val secondValueCallable = secondVariable::class.members.single { it.name == "getValue" }
            val firstValue = firstValueCallable.call(firstVariable)
            val secondValue = secondValueCallable.call(secondVariable)
            if (firstValue != null) {
                val availableFunctions = firstVariable::class.members.filter {
                    it.name == kClassOperatorName && it.parameters.size == 2 && ((secondValue != null && it.parameters[1].type == secondValue::class.createType()) || it.parameters[1].type == Any::class.createType(nullable = true))
                }
                if (availableFunctions.size == 1) {
                    val resultValue = availableFunctions[0].call(firstVariable, secondValue)
                    if (resultValue != null && typeMap[resultValue::class] != null) {
                        val variableToReturn = typeMap[resultValue::class]?.primaryConstructor?.call("")
                        if (variableToReturn != null) {
                            val setValueCallable =
                                variableToReturn::class.members.single { it.name == "setValue" }
                            setValueCallable.call(variableToReturn, resultValue)
                            returnedVariable = variableToReturn
                        } else {
                            //TODO error handling
                        }
                    } else {
                        //TODO error handling(result variable type is not implemented)
                    }
                } else {
                    //TODO error handling (incompatible types)
                }
            }
        } else {
            //TODO error handling
        }
    }
}