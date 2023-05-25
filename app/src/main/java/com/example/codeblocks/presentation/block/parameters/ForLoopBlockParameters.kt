package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.ForExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class ForLoopBlockParameters(
    var initBlock: BlockData? = null,
    var expressionBlock: ExpressionBlockData? = null,
    var postIterationBlock: BlockData? = null
): BlockParameters() {

    override fun createBundle(): ParamBundle {
        val initBlock = initBlock?.createBlock()
        val expressionBlock = expressionBlock?.createBlock()
        val postIterationBlock = postIterationBlock?.createBlock()
        return ForExpressionBlockBundle(
            initBlock,
            expressionBlock as ExpressionBlock?,
            postIterationBlock
        )
    }

}
