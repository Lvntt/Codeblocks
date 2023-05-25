package com.example.codeblocks.domain.entity.blocks.loop

import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle

class BreakBlock : StopExecutionBlock()
{

    override val paramType = EmptyParamBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {}

}