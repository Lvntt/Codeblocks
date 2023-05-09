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
import com.example.codeblocks.domain.entity.blocks.expression.operators.comparison.LessOrEqualCheckBlock
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