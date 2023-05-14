package com.example.codeblocks.domain.entity.parambundles.expression

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock

class ForExpressionBlockBundle(val firstBlock: Block, val expressionBlock: ExpressionBlock, val secondBlock: Block) : ParamBundle()