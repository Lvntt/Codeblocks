package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle

class EmptyParameters : BlockParameters() {
    override fun createBundle(): ParamBundle {
        return EmptyParamBundle()
    }
}