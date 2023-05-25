package com.example.codeblocks.presentation.block.parameters

import androidx.compose.runtime.mutableStateListOf
import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.variables.NullVariable
import kotlin.reflect.KClass

data class FunctionDeclarationParameters(
    var name: String = DefaultValues.EMPTY_STRING,
    var returnType: KClass<out Variable> = NullVariable::class,
    var paramsSignature: MutableList<Pair<String, KClass<out Variable>>> = mutableStateListOf(),
    val paramsVisibilityState: MutableList<Boolean> = mutableListOf()
): BlockParameters() {

    override fun createBundle(): ParamBundle {
        val functionSignature = FunctionSignature(name, returnType)
        paramsSignature.forEach {
            functionSignature.addParam(it.first, it.second)
        }
        return functionSignature
    }

}
