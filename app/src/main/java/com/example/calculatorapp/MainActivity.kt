package com.example.calculatorapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.Arrays
import java.util.Collections
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    //Declaring Array of String
    internal var Days = arrayOf(
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    )

    //Declaring all other variable
    lateinit var day:String
    lateinit var random:Random


    //Declaring Variable
    lateinit var txtRightAnswer: TextView
    lateinit var txtQuestionContainer: TextView
    lateinit var txtCorrectAnswer: TextView
    lateinit var etUserInput: EditText
    lateinit var btShow: Button
    lateinit var btCheck: Button
    lateinit var btNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txtRightAnswer = findViewById(R.id.txtRightAnswer)
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer)
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer)

        etUserInput = findViewById(R.id.etUserInput)

        btShow = findViewById(R.id.btShow)
        btCheck = findViewById(R.id.btCheck)
        btNext = findViewById(R.id.btNext)

        //intilize the random variable
        random = Random
        day = Days[random.nextInt(Days.size)]





        fun mixWords(word:String) : String {
            val word = Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile({it.isEmpty()}).toTypedArray())
            Collections.shuffle(word)
                var mixed = ""
                for (i in word){
                    mixed += i
                }
                return mixed
        }

        txtQuestionContainer.text = mixWords(day)

        btCheck.setOnClickListener{
            if (etUserInput.text.toString().equals(day, ignoreCase = true)){
                val dialog = Dialog(this@MainActivity)
                dialog.setContentView(R.layout.correct_dialog)
                val bthide = dialog.findViewById<Button>(R.id.btConfirmDialog)
                dialog.show()

                bthide.setOnClickListener{
                    dialog.dismiss()
                    day = Days[random.nextInt(Days.size)]
                    txtQuestionContainer.text = mixWords(day)
                    etUserInput.setText("")
                    txtRightAnswer.visibility = View.INVISIBLE
                    txtCorrectAnswer.visibility = View.INVISIBLE
                }
            }
            else{
                Toast.makeText(this@MainActivity, "You Failed :( ", Toast.LENGTH_SHORT).show()
            }
        }

        //Set the listener to the button Next
        btNext.setOnClickListener{
            day = Days[random.nextInt(Days.size)]
            txtQuestionContainer.text = mixWords(day)

            etUserInput.setText("")
            txtRightAnswer.visibility = View.INVISIBLE
            txtCorrectAnswer.visibility = View.INVISIBLE
        }

        btShow.setOnClickListener{
            txtRightAnswer.visibility = View.VISIBLE
            txtCorrectAnswer.visibility = View.VISIBLE


            txtRightAnswer.text = day
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}