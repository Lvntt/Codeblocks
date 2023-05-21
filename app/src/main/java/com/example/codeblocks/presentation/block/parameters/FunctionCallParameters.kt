package com.example.codeblocks.presentation.block.parameters

import androidx.compose.runtime.mutableStateListOf
import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class FunctionCallParameters(
    var name: String = DefaultValues.EMPTY_STRING,
    var passedParameters: MutableList<ExpressionBlockData> = mutableStateListOf(),
) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        val functionCallBundle = FunctionParamValues(name)
        passedParameters.forEach {
            functionCallBundle.addParam(
                it.createBlock() as ExpressionBlock
            )
        }
        return functionCallBundle
    }
}
