package com.example.desafio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var workingsTV: TextView
    lateinit var resultsTV: TextView

    var workings = ""
    var formula = ""
    var tempFormula = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTextViews()
    }

    private fun initTextViews() {
        workingsTV = findViewById(R.id.workingsTextView)
        resultsTV = findViewById(R.id.resultTextView)
    }

    private fun appendToWorkings(givenValue: String) {
        workings += givenValue
        workingsTV.text = workings
        formula = workings
    }

    fun equalsOnClick(view: View) {
        var result: Double? = null

        try {
            val expression = ExpressionBuilder(formula).build()
            result = expression.evaluate()
        } catch (e: ArithmeticException) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }

        result?.let {
            resultsTV.text = it.toString()
        }
    }

    fun clearOnClick(view: View) {
        workingsTV.text = ""
        workings = ""
        resultsTV.text = ""
        formula = ""
    }

    var leftBracket = true

    fun bracketsOnClick(view: View) {
        if (leftBracket) {
            appendToWorkings("(")
            leftBracket = false
        } else {
            appendToWorkings(")")
            leftBracket = true
        }
    }

    fun powerOfOnClick(view: View) {
        appendToWorkings("^")
    }

    fun divisionOnClick(view: View) {
        appendToWorkings("/")
    }

    fun sevenOnClick(view: View) {
        appendToWorkings("7")
    }

    fun eightOnClick(view: View) {
        appendToWorkings("8")
    }

    fun nineOnClick(view: View) {
        appendToWorkings("9")
    }

    fun timesOnClick(view: View) {
        appendToWorkings("*")
    }

    fun fourOnClick(view: View) {
        appendToWorkings("4")
    }

    fun fiveOnClick(view: View) {
        appendToWorkings("5")
    }

    fun sixOnClick(view: View) {
        appendToWorkings("6")
    }

    fun minusOnClick(view: View) {
        appendToWorkings("-")
    }

    fun oneOnClick(view: View) {
        appendToWorkings("1")
    }

    fun twoOnClick(view: View) {
        appendToWorkings("2")
    }

    fun threeOnClick(view: View) {
        appendToWorkings("3")
    }

    fun plusOnClick(view: View) {
        appendToWorkings("+")
    }

    fun decimalOnClick(view: View) {
        appendToWorkings(".")
    }

    fun zeroOnClick(view: View) {
        appendToWorkings("0")
    }
}