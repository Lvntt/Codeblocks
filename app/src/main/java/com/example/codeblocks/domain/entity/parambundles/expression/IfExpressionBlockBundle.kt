package com.example.codeblocks.domain.entity.parambundles.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.conditional.ElseBlock
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock

class IfExpressionBlockBundle(val expressionBlock: ExpressionBlock, val elseBlock: ElseBlock?) : ParamBundle()