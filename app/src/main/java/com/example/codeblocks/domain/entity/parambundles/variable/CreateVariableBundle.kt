package com.example.codeblocks.domain.entity.parambundles.variable

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class CreateVariableBundle(val name: String, val type: KClass<out Variable>) : ParamBundle()