package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import kotlin.reflect.KClass

abstract class BlockParameters {
    abstract val paramType: KClass<out BlockParameters>
    abstract fun createBundle(): ParamBundle

}