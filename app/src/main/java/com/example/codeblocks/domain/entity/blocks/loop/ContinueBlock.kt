package com.example.codeblocks.domain.entity.blocks.loop

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock

abstract class ContinueBlock : StopExecutionBlock()
{
    override val paramType = ParamBundle::class
    override fun executeAfterChecks(scope: Scope){}
}