package com.example.codeblocks.domain.entity.parambundles.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import kotlin.reflect.KClass

class ListExpressionBlockBundle(val type: KClass<out Variable>) : ParamBundle() {
    private val _elements: MutableList<ExpressionBlock> = mutableListOf()
    val elements: List<ExpressionBlock> = _elements

    fun addElement(expression: ExpressionBlock) {
        _elements.add(expression)
    }
}