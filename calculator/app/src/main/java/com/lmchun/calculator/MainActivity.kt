package com.lmchun.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lmchun.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    var number : String? = null
    var firstNumber : Double = 0.0
    var lastNumber : Double = 0.0
    var status : String? = null
    var operator : Boolean = false

    val myFormatter = DecimalFormat("######.######")
    var history : String = ""
    var currentResult : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        enableEdgeToEdge()
        setContentView(view)
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        mainBinding.textViewResult.text = "0"

        mainBinding.btn0.setOnClickListener{
         onNumberClicked("0")
        }
        mainBinding.btn1.setOnClickListener{
            onNumberClicked("1")
        }
        mainBinding.btn2.setOnClickListener{
            onNumberClicked("2")
        }
        mainBinding.btn3.setOnClickListener{
            onNumberClicked("3")
        }
        mainBinding.btn4.setOnClickListener{
            onNumberClicked("4")
        }
        mainBinding.btn5.setOnClickListener{
            onNumberClicked("5")
        }
        mainBinding.btn6.setOnClickListener{
            onNumberClicked("6")
        }
        mainBinding.btn7.setOnClickListener{
            onNumberClicked("7")
        }
        mainBinding.btn8.setOnClickListener{
            onNumberClicked("8")
        }
        mainBinding.btn9.setOnClickListener{
            onNumberClicked("9")
        }
        mainBinding.btnAC.setOnClickListener{
            onNumberACClicked()
        }
        mainBinding.btnDel.setOnClickListener{
            number?.let {
                number = it.substring(0,it.length-1)
                mainBinding.textViewResult.text = number
            }
        }
        mainBinding.btnDivide.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(currentResult).plus("/")

            if(operator){
                when(status){
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()

                }
            }
            status = "division"
            operator = false
            number = null
        }
        mainBinding.btnMulti.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(currentResult).plus("*")

            if(operator){
                when(status){
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()

                }
            }
            status =  "multiplication"
            operator = false
            number = null
        }
        mainBinding.btnMinus.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(currentResult).plus("-")

            if(operator){
                when(status){
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()

                }
            }
            status = "subtraction"
            operator = false
            number = null
        }
        mainBinding.btnPlus.setOnClickListener{

            history = mainBinding.textViewHistory.text.toString()
            currentResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(currentResult).plus("+")

            if(operator){
                when(status){
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()

                }
            }
            status = "addition"
            operator = false
            number = null
        }
        mainBinding.btnEquals.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult = mainBinding.textViewResult.text.toString()

            if(operator){
                when(status){
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()

                }
                mainBinding.textViewHistory.text = history.plus(currentResult).plus("=").plus(mainBinding.textViewResult.text.toString())
            }
            operator = false
        }
        mainBinding.btnDot.setOnClickListener{
            number = if(number == null){
                "0."
            }else{
                "$number."

            }
        mainBinding.textViewResult.text = number
        }


    }

    fun onNumberACClicked(){
        number = null
        status = null
        mainBinding.textViewResult.text = "0"
        mainBinding.textViewHistory.text = ""
        firstNumber = 0.0
        lastNumber = 0.0

    }

    fun onNumberClicked(clickedNumber : String){
     if (number == null){
         number = clickedNumber
     }else {
         number += clickedNumber
     }
     mainBinding.textViewResult.text = number
    operator = true
    }
    fun plus(){
    lastNumber = mainBinding.textViewResult.text.toString().toDouble()
    firstNumber += lastNumber
    mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }
    fun minus(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        firstNumber -= lastNumber
        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }
    fun multiply(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        firstNumber *= lastNumber
        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }
    fun divide(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        if(lastNumber == 0.0){
            Toast.makeText(applicationContext, "The divisor cannot be zero", Toast.LENGTH_LONG).show()
        }else{
            firstNumber /= lastNumber
            mainBinding.textViewResult.text = myFormatter.format(firstNumber)
        }
    }

}