package com.example.codeblocks.presentation.block.parameters

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.conditional.ElseBlock
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.IfExpressionBlockBundle
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData

data class IfBlockParameters(
    var expression: ExpressionBlockData? = null,
    var elseBlock: BlockWithNestingData? = null
) : BlockParameters() {

    override fun createBundle(): ParamBundle {
        val expression = expression
            ?: // TODO catch in vm
            throw Exception()
        return IfExpressionBlockBundle(
            expression.createBlock() as ExpressionBlock,
            elseBlock?.createBlock() as ElseBlock?
        )
    }

}
