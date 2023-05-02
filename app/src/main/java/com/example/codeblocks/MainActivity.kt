package com.example.codeblocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.codeblocks.domain.entity.parambundles.expression.VariableNameBundle
import com.example.codeblocks.domain.entity.parambundles.expression.VariableValueBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.domain.entity.parambundles.function.FunctionReturnBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.ui.theme.CodeblocksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fibonacciTest("0").execute()
//        fibonacciTest("1").execute()
//        fibonacciTest("2").execute()
//        fibonacciTest("3").execute()
//        fibonacciTest("4").execute()
//        fibonacciTest("5").execute()
//        fibonacciTest("6").execute()
//        fibonacciTest("7").execute()
//        fibonacciTest("8").execute()
//        fibonacciTest("9").execute()
//        fibonacciTest("10").execute()
//        fibonacciTest("11").execute()
//        fibonacciTest("12").execute()
//        fibonacciTest("13").execute()
//        fibonacciTest("14").execute()
//        fibonacciTest("15").execute()
//        fibonacciTest("16").execute()
//        fibonacciTest("17").execute()
//        fibonacciTest("18").execute()
//        val finalTest = fibonacciTest("19")
//        finalTest.execute()
//        finalTest.execute()
        setContent {
            CodeblocksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeblocksTheme {
        Greeting("Android")
    }
}

fun fibonacciTest(n: String): Program {
    val program = Program()
    val fibFunctionDeclarator = FunctionDeclaratorBlock()
    val fibFunctionSignature = FunctionSignature("fib", IntegerVariable::class)
    fibFunctionSignature.addParam("n", IntegerVariable::class)
    fibFunctionDeclarator.setParams(fibFunctionSignature)
    val firstIf = IfBlock()
    val secondIf = IfBlock()
    val nValueBlock = VariableByNameBlock()
    val nValueBundle = VariableNameBundle("n")
    nValueBlock.setParams(nValueBundle)
    val zeroValueBlock = VariableByValueBlock()
    val zeroValueBundle = VariableValueBundle("0")
    zeroValueBlock.setParams(zeroValueBundle)
    val oneValueBlock = VariableByValueBlock()
    val oneValueBundle = VariableValueBundle("1")
    oneValueBlock.setParams(oneValueBundle)
    val twoValueBlock = VariableByValueBlock()
    val twoValueBundle = VariableValueBundle("2")
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
    val inputValueBundle = VariableValueBundle(n)
    inputValueBlock.setParams(inputValueBundle)
    functionParamValues.addParam(inputValueBlock)
    functionCallBlock.setParams(functionParamValues)
    printFibBlock.setParams(SingleExpressionBlockBundle(functionCallBlock))
    program.blocks.add(printFibBlock)
    return program
}