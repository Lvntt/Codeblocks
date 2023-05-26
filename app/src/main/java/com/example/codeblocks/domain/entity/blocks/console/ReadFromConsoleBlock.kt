package com.example.codeblocks.domain.entity.blocks.console

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import com.example.codeblocks.domain.entity.variables.VariableTypeMap
import com.example.codeblocks.domain.entity.variables.VariableTypeMap.convertStringToPrimitiveValue
import com.example.codeblocks.domain.usecases.ReadFromConsoleUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class ReadFromConsoleBlock: ExpressionBlock(), KoinComponent {

    override val paramType: KClass<out ParamBundle> = EmptyParamBundle::class
    private val readFromConsoleUseCase: ReadFromConsoleUseCase by inject()

    override suspend fun executeAfterChecks(scope: Scope) {
        val value = convertStringToPrimitiveValue(readFromConsoleUseCase())
        returnedVariable = if (value != null) {
            val variable = VariableTypeMap.typeMap[value::class]?.primaryConstructor?.call(
                DefaultValues.EMPTY_STRING) ?: /*TODO error handling*/ throw Exception()
            variable.setValue(value)
            variable
        } else {
            NullVariable(DefaultValues.EMPTY_STRING)
        }
    }

}