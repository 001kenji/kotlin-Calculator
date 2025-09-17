package com.example.advancedcalc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.advancedcalc.databinding.ActivityMainBinding
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.round

var FirstDigits = ""
var SecondDigits = ""
var ResultValue = ""
var OparatorValue = ""

class MainActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ClearButton.setOnClickListener {
            binding.InputDisplayField.text = ""
            binding .ResultTextView.text = ""
        }

        binding.PercentageButton.setOnClickListener {
            val operatorInput = "%"
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + operatorInput
            val parts = inputData.split(operatorInput)   // returns a List<String>
            val beforeSlash = parts[0]         // "123"
            val afterSlash = if (parts.size > 1) parts[1] else ""  // "456" (safe check)
            FirstDigits = beforeSlash
            OparatorValue = operatorInput

        }

        binding.DeleteButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            if (inputData.isNotEmpty()) {
                binding.InputDisplayField.text = inputData.dropLast(1)
            }
        }

        binding.DivideButton.setOnClickListener {
            val operatorInput = "/"
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + operatorInput
            val parts = inputData.split(operatorInput)   // returns a List<String>
            val beforeSlash = parts[0]         // "123"
            val afterSlash = if (parts.size > 1) parts[1] else ""  // "456" (safe check)
            FirstDigits = beforeSlash
            OparatorValue = operatorInput

        }

        binding.SevenButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "7"

        }

        binding.EightButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "8"

        }
        binding.NineButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "9"

        }
        binding.MultiplyButton.setOnClickListener {
            val operatorInput = "*"
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + operatorInput
            val parts = inputData.split(operatorInput)   // returns a List<String>
            val beforeSlash = parts[0]         // "123"
            val afterSlash = if (parts.size > 1) parts[1] else ""  // "456" (safe check)
            FirstDigits = beforeSlash
            OparatorValue = operatorInput

        }

        binding.FourButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "4"

        }
        binding.FiveButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "5"

        }
        binding.SixButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "6"

        }
        binding.MinusButton.setOnClickListener {
            val operatorInput = "-"
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + operatorInput
            val parts = inputData.split(operatorInput)   // returns a List<String>
            val beforeSlash = parts[0]         // "123"
            val afterSlash = if (parts.size > 1) parts[1] else ""  // "456" (safe check)
            FirstDigits = beforeSlash
            OparatorValue = operatorInput

        }
        binding.OneButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "1"

        }
        binding.TwoButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "2"

        }
        binding.ThreeButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "3"

        }
        binding.PlusButton.setOnClickListener {
            val operatorInput = "+"
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + operatorInput
            val parts = inputData.split(operatorInput)   // returns a List<String>
            val beforeSlash = parts[0]         // "123"
            val afterSlash = if (parts.size > 1) parts[1] else ""  // "456" (safe check)
            FirstDigits = beforeSlash
            OparatorValue = operatorInput

        }
        binding.DoubleZeroButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "00"

        }
        binding.ZeroButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "0"

        }
        binding.DecimalButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()
            binding.InputDisplayField.text = inputData + "."

        }

        binding.EqualsButton.setOnClickListener {
            val inputData = binding.InputDisplayField.text.toString()

            try {
                // Evaluate the expression with BODMAS
                val result = evaluateExpression(inputData)

                println("Expression: $inputData = $result")

                binding.ResultTextView.text = result.toString()
            } catch (e: Exception) {
                binding.ResultTextView.text = "Error"
                println("Error: ${e.message}")
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()

            }
        }




    }
}
fun evaluateExpression(expr: String): Double {
    val expression = expr.replace("%", " % ") // space out operators
        .replace("+", " + ")
        .replace("-", " - ")
        .replace("*", " * ")
        .replace("/", " / ")
        .trim()

    val tokens = expression.split("\\s+".toRegex())

    val values = java.util.Stack<Double>()
    val ops = java.util.Stack<String>()

    fun applyOp(op: String, b: Double, a: Double): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else throw ArithmeticException("Divide by zero")
            "%" -> if (b != 0.0) a % b else throw ArithmeticException("Modulo by zero")
            else -> 0.0
        }
    }

    fun precedence(op: String): Int {
        return when (op) {
            "+", "-" -> 1
            "*", "/", "%" -> 2
            else -> 0
        }
    }

    for (token in tokens) {
        when {
            token.matches("\\d+(\\.\\d+)?".toRegex()) -> values.push(token.toDouble())
            token in listOf("+", "-", "*", "/", "%") -> {
                while (ops.isNotEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(token)
            }
        }
    }

    while (ops.isNotEmpty()) {
        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
    }

    return values.pop().roundTo(12)
}



// Extension function to round Double to N decimals
fun Double.roundTo(n: Int): Double {
    val factor = 10.0.pow(n)
    return round(this * factor) / factor
}

