package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle
import kotlin.reflect.KClass

data class StringExpressionParameter(
    var name: String = DefaultValues.EMPTY_STRING,
    override val paramType: KClass<out BlockParameters> = StringExpressionParameter::class
) : BlockParameters() {

    override fun createBundle(): ParamBundle = VariableBundle(name)

}
