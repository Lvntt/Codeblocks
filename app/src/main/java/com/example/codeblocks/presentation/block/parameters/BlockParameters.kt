package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle

abstract class BlockParameters {
    abstract fun createBundle(): ParamBundle
}