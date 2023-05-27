package com.example.codeblocks.domain.entity.parambundles.variable

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock

class SetVariableBundle(val name: String, val expression: ExpressionBlock) : ParamBundle()