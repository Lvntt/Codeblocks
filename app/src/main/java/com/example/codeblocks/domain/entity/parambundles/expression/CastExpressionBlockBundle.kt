package com.example.codeblocks.domain.entity.parambundles.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import kotlin.reflect.KClass

class CastExpressionBlockBundle(val expressionBlock: ExpressionBlock, val castTo: KClass<out Variable>): ParamBundle()