package com.example.codeblocks.ui

import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import com.example.codeblocks.domain.entity.variables.ByteVariable
import com.example.codeblocks.domain.entity.variables.DoubleVariable
import com.example.codeblocks.domain.entity.variables.FloatVariable
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.domain.entity.variables.ListVariable
import com.example.codeblocks.domain.entity.variables.LongVariable
import com.example.codeblocks.domain.entity.variables.NullVariable
import com.example.codeblocks.domain.entity.variables.ShortVariable

object AvailableVariableTypes {

    val typenameToKClass = mapOf(
        R.string.byteType to ByteVariable::class,
        R.string.shortType to ShortVariable::class,
        R.string.intType to IntegerVariable::class,
        R.string.longType to LongVariable::class,
        R.string.floatType to FloatVariable::class,
        R.string.doubleType to DoubleVariable::class,
        R.string.booleanType to BooleanVariable::class,
    )

    val functionTypenameToKClass = typenameToKClass.plus(
        Pair(
            R.string.nullType, NullVariable::class,
        )
    )

    val listTypenameToKClass = typenameToKClass.plus(
        Pair(
            R.string.listType, ListVariable::class
        )
    )

}