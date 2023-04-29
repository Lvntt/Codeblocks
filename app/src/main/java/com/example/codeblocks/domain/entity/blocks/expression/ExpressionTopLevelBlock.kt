package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import kotlin.reflect.KClass

class ExpressionTopLevelBlock: ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class
    override fun executeAfterChecks() {
            returnedVariable =
                getVariableFromParams((paramBundle as SingleExpressionBlockBundle).expressionBlock)
    }
}