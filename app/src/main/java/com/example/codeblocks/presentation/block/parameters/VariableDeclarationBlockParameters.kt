package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import kotlin.reflect.KClass

data class VariableDeclarationBlockParameters(
    var type: KClass<out Variable> = IntegerVariable::class,
    var name: String = ""
) : BlockParameters()
