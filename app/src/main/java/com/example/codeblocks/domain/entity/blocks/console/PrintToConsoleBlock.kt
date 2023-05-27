package com.example.codeblocks.domain.entity.blocks.console

import android.util.Log
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.usecases.WriteToConsoleUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KClass

class PrintToConsoleBlock : Block(), KoinComponent {

    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class
    private val writeToConsoleUseCase: WriteToConsoleUseCase by inject()

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
        val returnResult = (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue() ?: /*TODO error handling*/ throw Exception()

        writeToConsoleUseCase.writeOutputToConsole(returnResult.toString())
    }

}