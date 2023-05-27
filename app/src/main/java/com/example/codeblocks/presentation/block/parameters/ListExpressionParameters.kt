package com.example.codeblocks.presentation.block.parameters

import androidx.compose.runtime.mutableStateListOf
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.ListExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import kotlin.reflect.KClass

data class ListExpressionParameters(
    var type: KClass<out Variable> = IntegerVariable::class,
    var expressionList: MutableList<ExpressionBlockData> = mutableStateListOf()
) : BlockParameters() {

    override fun createBundle(): ParamBundle {
        val bundle = ListExpressionBlockBundle(type)
        expressionList.forEach {
            bundle.addElement(it.createBlock() as ExpressionBlock)
        }
        return bundle
    }

}