package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle
import kotlin.reflect.KClass

class EmptyParameters(override val paramType: KClass<out BlockParameters> = EmptyParameters::class) : BlockParameters() {
    override fun createBundle(): ParamBundle {
        return EmptyParamBundle()
    }

}