package com.example.codeblocks

import com.example.codeblocks.domain.entity.Program
import com.example.codeblocks.domain.entity.blocks.conditional.ElseBlock
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.EqualityCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.MoreOrEqualCheckBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.logic.AndBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.logic.OrBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.MinusBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.PlusBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionCallBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.domain.entity.blocks.loop.ForBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle
import com.example.codeblocks.domain.entity.parambundles.expression.ForExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.IfExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.TwoExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.parambundles.variable.CreateVariableBundle
import com.example.codeblocks.domain.entity.parambundles.variable.SetVariableBundle
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
    val firstIfExpression = IfExpressionBlockBundle(firstComparisonBlock, null)
    val secondIfExpression = IfExpressionBlockBundle(secondComparisonBlock, null)
    firstIf.setParams(firstIfExpression)
    secondIf.setParams(secondIfExpression)
    val firstReturnBlock = FunctionReturnBlock()
    val secondReturnBlock = FunctionReturnBlock()
    val firstReturnExpression = SingleExpressionBlockBundle(zeroValueBlock)
    val secondReturnExpression = SingleExpressionBlockBundle(oneValueBlock)
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
    thirdReturnBlock.setParams(SingleExpressionBlockBundle(thirdReturnExpressionBlock))
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

fun whileTest(n: String): Program {
    val program = Program()
    val variableDeclaratorBlock = CreateVariableBlock()
    val variableDeclaratorBundle = CreateVariableBundle("n", IntegerVariable::class)
    variableDeclaratorBlock.setParams(variableDeclaratorBundle)
    val inputValueBlock = VariableByValueBlock()
    val inputValueBundle = VariableBundle(n)
    inputValueBlock.setParams(inputValueBundle)
    val setVariableBlock = SetVariableBlock()
    val setVariableBundle = SetVariableBundle("n", inputValueBlock)
    setVariableBlock.setParams(setVariableBundle)
    val whileBlock = DoWhileBlock()
    val nValueBlock = VariableByNameBlock()
    val nValueBundle = VariableBundle("n")
    nValueBlock.setParams(nValueBundle)
    val zeroValueBlock = VariableByValueBlock()
    val zeroValueBundle = VariableBundle("0")
    zeroValueBlock.setParams(zeroValueBundle)
    val oneValueBlock = VariableByValueBlock()
    val oneValueBundle = VariableBundle("1")
    oneValueBlock.setParams(oneValueBundle)
    val moreCheckBlock = MoreOrEqualCheckBlock()
    moreCheckBlock.setParams(TwoExpressionBlockBundle(nValueBlock, zeroValueBlock))
    whileBlock.setParams(SingleExpressionBlockBundle(moreCheckBlock))
    val printCons = PrintToConsoleBlock()
    printCons.setParams(SingleExpressionBlockBundle(nValueBlock))
    val setMinusBlock = SetVariableBlock()
    val minusBlock = MinusBlock()
    minusBlock.setParams(TwoExpressionBlockBundle(nValueBlock, oneValueBlock))
    setMinusBlock.setParams(SetVariableBundle("n", minusBlock))
    whileBlock.nestedBlocks.add(printCons)
    whileBlock.nestedBlocks.add(setMinusBlock)
    program.blocks.add(variableDeclaratorBlock)
    program.blocks.add(setVariableBlock)
    program.blocks.add(whileBlock)
    return program
}

fun forTest(n: String): Program {
    val program = Program()
    val variableDeclaratorBlock = CreateVariableBlock()
    val variableDeclaratorBundle = CreateVariableBundle("n", IntegerVariable::class)
    variableDeclaratorBlock.setParams(variableDeclaratorBundle)
    val inputValueBlock = VariableByValueBlock()
    val inputValueBundle = VariableBundle(n)
    inputValueBlock.setParams(inputValueBundle)
    val setVariableBlock = SetVariableBlock()
    val setVariableBundle = SetVariableBundle("n", inputValueBlock)
    setVariableBlock.setParams(setVariableBundle)
    val twoVariableDeclaratorBlock = CreateVariableBlock()
    val twoVariableDeclaratorBundle = CreateVariableBundle("i", IntegerVariable::class)
    twoVariableDeclaratorBlock.setParams(twoVariableDeclaratorBundle)
    val zeroValueBlock = VariableByValueBlock()
    val zeroValueBundle = VariableBundle("0")
    zeroValueBlock.setParams(zeroValueBundle)
    val twoSetVariableBlock = SetVariableBlock()
    val twoSetVariableBundle = SetVariableBundle("i", zeroValueBlock)
    twoSetVariableBlock.setParams(twoSetVariableBundle)
    val whileBlock = ForBlock()
    val nValueBlock = VariableByNameBlock()
    val nValueBundle = VariableBundle("n")
    nValueBlock.setParams(nValueBundle)
    val iValueBlock = VariableByNameBlock()
    val iValueBundle = VariableBundle("i")
    iValueBlock.setParams(iValueBundle)
    val oneValueBlock = VariableByValueBlock()
    val oneValueBundle = VariableBundle("1")
    oneValueBlock.setParams(oneValueBundle)
    val setMinusBlock = SetVariableBlock()
    val plusBlock = PlusBlock()
    plusBlock.setParams(TwoExpressionBlockBundle(iValueBlock, oneValueBlock))
    setMinusBlock.setParams(SetVariableBundle("i", plusBlock))
    val moreCheckBlock = LessOrEqualCheckBlock()
    moreCheckBlock.setParams(TwoExpressionBlockBundle(iValueBlock, nValueBlock))
    whileBlock.setParams(ForExpressionBlockBundle(zeroValueBlock, moreCheckBlock, setMinusBlock))
    val printCons = PrintToConsoleBlock()
    printCons.setParams(SingleExpressionBlockBundle(iValueBlock))
    whileBlock.nestedBlocks.add(printCons)
    program.blocks.add(variableDeclaratorBlock)
    program.blocks.add(setVariableBlock)
    program.blocks.add(twoVariableDeclaratorBlock)
    program.blocks.add(twoSetVariableBlock)
    program.blocks.add(whileBlock)
    return program
}

fun ifTest(n: String): Program {
    val program = Program()
    val variableDeclaratorBlock = CreateVariableBlock()
    val variableDeclaratorBundle = CreateVariableBundle("n", IntegerVariable::class)
    variableDeclaratorBlock.setParams(variableDeclaratorBundle)
    val inputValueBlock = VariableByValueBlock()
    val inputValueBundle = VariableBundle(n)
    inputValueBlock.setParams(inputValueBundle)
    val setVariableBlock = SetVariableBlock()
    val setVariableBundle = SetVariableBundle("n", inputValueBlock)
    setVariableBlock.setParams(setVariableBundle)
    val ifBlock = IfBlock()
    val nValueBlock = VariableByNameBlock()
    val nValueBundle = VariableBundle("n")
    nValueBlock.setParams(nValueBundle)
    val fiveValueBlock = VariableByValueBlock()
    val fiveValueBundle = VariableBundle("5")
    fiveValueBlock.setParams(fiveValueBundle)
    val minusFiveValueBlock = VariableByValueBlock()
    val minusFiveValueBundle = VariableBundle("-5")
    minusFiveValueBlock.setParams(minusFiveValueBundle)
    val tenValueBlock = VariableByValueBlock()
    val tenValueBundle = VariableBundle("10")
    tenValueBlock.setParams(tenValueBundle)
    val minusTenValueBlock = VariableByValueBlock()
    val minusTenValueBundle = VariableBundle("-10")
    minusTenValueBlock.setParams(minusTenValueBundle)
    val oneValueBlock = VariableByValueBlock()
    val oneValueBundle = VariableBundle("1")
    oneValueBlock.setParams(oneValueBundle)
    val minusOneValueBlock = VariableByValueBlock()
    val minusOneValueBundle = VariableBundle("-1")
    minusOneValueBlock.setParams(minusOneValueBundle)
    val firstComparisonBlock = MoreOrEqualCheckBlock()
    val firstComparisonBundle = TwoExpressionBlockBundle(nValueBlock, fiveValueBlock)
    firstComparisonBlock.setParams(firstComparisonBundle)
    val secondComparisonBlock = LessOrEqualCheckBlock()
    val secondComparisonBundle = TwoExpressionBlockBundle(nValueBlock, tenValueBlock)
    secondComparisonBlock.setParams(secondComparisonBundle)
    val firstAndComparisonBlock = AndBlock()
    firstAndComparisonBlock.setParams(TwoExpressionBlockBundle(firstComparisonBlock, secondComparisonBlock))
    val thirdComparisonBlock = MoreCheckBlock()
    val thirdComparisonBundle = TwoExpressionBlockBundle(nValueBlock, minusTenValueBlock)
    thirdComparisonBlock.setParams(thirdComparisonBundle)
    val fourthComparisonBlock = LessCheckBlock()
    val fourthComparisonBundle = TwoExpressionBlockBundle(nValueBlock, minusFiveValueBlock)
    fourthComparisonBlock.setParams(fourthComparisonBundle)
    val secondAndComparisonBlock = AndBlock()
    secondAndComparisonBlock.setParams(TwoExpressionBlockBundle(thirdComparisonBlock, fourthComparisonBlock))
    val comparisonBlock = OrBlock()
    comparisonBlock.setParams(TwoExpressionBlockBundle(firstAndComparisonBlock, secondAndComparisonBlock))
    val printMinus = PrintToConsoleBlock()
    printMinus.setParams(SingleExpressionBlockBundle(minusOneValueBlock))
    val printPlus = PrintToConsoleBlock()
    printPlus.setParams(SingleExpressionBlockBundle(oneValueBlock))
    val elseBlock = ElseBlock()
    elseBlock.setParams(EmptyParamBundle())
    elseBlock.nestedBlocks.add(printMinus)
    val ifExpression = IfExpressionBlockBundle(comparisonBlock, elseBlock)
    ifBlock.setParams(ifExpression)
    ifBlock.nestedBlocks.add(printPlus)
    program.blocks.add(variableDeclaratorBlock)
    program.blocks.add(setVariableBlock)
    program.blocks.add(ifBlock)
    return program
}