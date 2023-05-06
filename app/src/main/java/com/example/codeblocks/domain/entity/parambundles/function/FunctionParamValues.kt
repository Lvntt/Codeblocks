package com.example.codeblocks.domain.entity.parambundles.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock

class FunctionParamValues(val functionName: String) : ParamBundle() {

    private val _expressionList: MutableList<ExpressionBlock> = mutableListOf()
    val expressionList: List<ExpressionBlock> = _expressionList

    fun addParam(expression: ExpressionBlock) {
        _expressionList.add(expression)
    }

}