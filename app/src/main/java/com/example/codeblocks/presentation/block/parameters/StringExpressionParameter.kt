package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle

data class StringExpressionParameter(
    var name: String = DefaultValues.EMPTY_STRING
) : BlockParameters() {
    override fun createBundle(): ParamBundle = VariableBundle(name)
}
