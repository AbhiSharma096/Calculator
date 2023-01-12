package com.example.calculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    private var tvinput: TextView? = null
    var lastnumeric : Boolean = false
    var lastdot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.tvinput)
    }
    fun Ondigit (view: View){
       //Toast.makeText(this,"Button Clicked" , Toast.LENGTH_LONG).show()
        tvinput?.append((view as Button).text)
        lastnumeric=true


    }
    fun onclr(view: View){
        tvinput?.text= ""
        lastdot=false
        lastnumeric=false
    }

    fun clickdot(view: View){
        if (lastnumeric && !lastdot){
            tvinput?.append(".")
            lastnumeric = false
            lastdot= true
        }
    }

    fun OnOperator(view: View){
        tvinput?.text?.let {
            if (lastnumeric && !opadded(it.toString())){
                tvinput?.append((view as Button).text)
                lastdot=false
                lastnumeric=false

            }
        }

    }
     fun onequal(view: View){

             if (lastnumeric) {

             var tvValue = tvinput?.text.toString()
             var prefix = ""
             try {


                 if (tvValue.startsWith("-")) {
                     prefix = "-"
                     tvValue = tvValue.substring(1);
                 }


                 when {
                     tvValue.contains("÷") -> {

                         val splitedValue = tvValue.split("÷")

                         var one = splitedValue[0]
                         val two = splitedValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         tvinput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                     }
                     tvValue.contains("×") -> {

                         val splitedValue = tvValue.split("×")


                         var one = splitedValue[0]
                         val two = splitedValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         tvinput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                     }
                     tvValue.contains("-") -> {


                         val splitedValue = tvValue.split("-")

                         var one = splitedValue[0]
                         val two = splitedValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         tvinput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                     }
                     tvValue.contains("+") -> {

                         val splitedValue = tvValue.split("+")

                         var one = splitedValue[0]
                         val two = splitedValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         tvinput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                     }

                 }
             } catch (e: ArithmeticException) {
                 e.printStackTrace()
             }
         }
     }

    fun percent (view: View){
        if (!lastnumeric || lastdot){
            return
        }
        var tvValue: String = tvinput?.text.toString()

        if (!tvValue.contains("+") || !tvValue.contains("-") || !tvValue.contains("×") || !tvValue.contains("÷") || !tvValue.isEmpty()) {
            var Value = tvinput?.text.toString()
            var exvalue = Value.toDouble()
            tvinput?.text = removeZeroAfterDot((exvalue / 100).toString())
        }

    }



    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }

    fun backspace(view: View){

        tvinput?.text?.let {
            var value = it.toString()
            if (value.length>=1){
                tvinput?.text = value.substring(0, value.length - 1)
            }
            else {

            }


        }
    }

    private fun opadded(value : String): Boolean{
        return if(value.startsWith("-")){
            false
        } else {
            (value.contains("+") ||
                    value.contains("÷") ||
                    value.contains("×") ||
                    value.contains("-"))
        }
    }
}