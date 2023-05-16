package com.example.codeblocks

import com.example.codeblocks.domain.entity.Program
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.EqualityCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MinusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.PlusBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionCallBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.domain.entity.parambundles.function.FunctionReturnBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.variables.IntegerVariable

fun fibonacciTest(n: String): Program {
    val program = Program()
    val fibFunctionDeclarator = FunctionDeclaratorBlock()
    val fibFunctionSignature = FunctionSignature("fib", IntegerVariable::class)
    fibFunctionSignature.addParam("n", IntegerVariable::class)
    fibFunctionDeclarator.setParams(fibFunctionSignature)
    val firstIf = IfBlock()
    val secondIf = IfBlock()
    val nValueBlock = VariableByNameBlock()
    val nValueBundle = VariableBundle("n")
    nValueBlock.setParams(nValueBundle)
    val zeroValueBlock = VariableByValueBlock()
    val zeroValueBundle = VariableBundle("0")
    zeroValueBlock.setParams(zeroValueBundle)
    val oneValueBlock = VariableByValueBlock()
    val oneValueBundle = VariableBundle("1")
    oneValueBlock.setParams(oneValueBundle)
    val twoValueBlock = VariableByValueBlock()
    val twoValueBundle = VariableBundle("2")
    twoValueBlock.setParams(twoValueBundle)
    val firstComparisonBlock = EqualityCheckBlock()
    val firstComparisonBundle = TwoExpressionBlockBundle(nValueBlock, zeroValueBlock)
    firstComparisonBlock.setParams(firstComparisonBundle)
    val secondComparisonBlock = EqualityCheckBlock()
    val secondComparisonBundle = TwoExpressionBlockBundle(nValueBlock, oneValueBlock)
    secondComparisonBlock.setParams(secondComparisonBundle)
    val firstIfExpression = SingleExpressionBlockBundle(firstComparisonBlock)
    val secondIfExpression = SingleExpressionBlockBundle(secondComparisonBlock)
    firstIf.setParams(firstIfExpression)
    secondIf.setParams(secondIfExpression)
    val firstReturnBlock = FunctionReturnBlock()
    val secondReturnBlock = FunctionReturnBlock()
    val firstReturnExpression = FunctionReturnBundle("fib",zeroValueBlock)
    val secondReturnExpression = FunctionReturnBundle("fib",oneValueBlock)
    firstReturnBlock.setParams(firstReturnExpression)
    secondReturnBlock.setParams(secondReturnExpression)
    firstIf.nestedBlocks.add(firstReturnBlock)
    secondIf.nestedBlocks.add(secondReturnBlock)
    fibFunctionDeclarator.nestedBlocks.add(firstIf)
    fibFunctionDeclarator.nestedBlocks.add(secondIf)
    val thirdReturnBlock = FunctionReturnBlock()
    val thirdReturnExpressionBlock = PlusBlock()
    val firstFunctionCallBlock = FunctionCallBlock()
    val secondFunctionCallBlock = FunctionCallBlock()
    val firstFunctionCallExpBlock = MinusBlock()
    firstFunctionCallExpBlock.setParams(TwoExpressionBlockBundle(nValueBlock,oneValueBlock))
    val secondFunctionCallExpBlock = MinusBlock()
    secondFunctionCallExpBlock.setParams(TwoExpressionBlockBundle(nValueBlock,twoValueBlock))
    val firstFunctionCallExpBundle = FunctionParamValues("fib")
    firstFunctionCallExpBundle.addParam(firstFunctionCallExpBlock)
    val secondFunctionCallExpBundle = FunctionParamValues("fib")
    secondFunctionCallExpBundle.addParam(secondFunctionCallExpBlock)
    firstFunctionCallBlock.setParams(firstFunctionCallExpBundle)
    secondFunctionCallBlock.setParams(secondFunctionCallExpBundle)
    thirdReturnExpressionBlock.setParams(TwoExpressionBlockBundle(firstFunctionCallBlock, secondFunctionCallBlock))
    thirdReturnBlock.setParams(FunctionReturnBundle("fib",thirdReturnExpressionBlock))
    fibFunctionDeclarator.nestedBlocks.add(thirdReturnBlock)
    program.blocks.add(fibFunctionDeclarator)
    val printFibBlock = PrintToConsoleBlock()
    val functionCallBlock = FunctionCallBlock()
    val functionParamValues = FunctionParamValues("fib")
    val inputValueBlock = VariableByValueBlock()
    val inputValueBundle = VariableBundle(n)
    inputValueBlock.setParams(inputValueBundle)
    functionParamValues.addParam(inputValueBlock)
    functionCallBlock.setParams(functionParamValues)
    printFibBlock.setParams(SingleExpressionBlockBundle(functionCallBlock))
    program.blocks.add(printFibBlock)
    return program
}