package com.example.exercise4_epf_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dob : Int = 0

    private lateinit var txtDOB : TextView
    private lateinit var txtSaving : TextView
    private lateinit var txtAge : TextView
    private lateinit var txtInves : TextView
    private lateinit var  btnClear : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myFormat = "dd.MM.yyyy"

        txtDOB = findViewById(R.id.txtDoB)
        txtAge = findViewById(R.id.txtAge)
        txtSaving = findViewById(R.id.txtSaving)
        txtInves = findViewById(R.id.txtInvest)
        btnClear = findViewById(R.id.btnClr)

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        txtDOB.text = sdf.format(System.currentTimeMillis())

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            txtDOB.text = sdf.format(cal.time)
            dob = cal.get(Calendar.YEAR)

            txtAge.text = calculateAge(dob).toString()
            txtSaving.text  = calculateBasicSaving(calculateAge(dob)).toString()
            txtInves.text = calculateInvestment().toString()
        }

        txtDOB.setOnClickListener{
            DatePickerDialog(this@MainActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnClear.setOnClickListener{
            txtInves.text = ""
            txtSaving.text = ""
            txtAge.text  = ""
            txtDOB.text = sdf.format(System.currentTimeMillis())
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateInvestment(): Double {
        return calculateBasicSaving(calculateAge(dob)) * 0.3
    }

    private fun calculateBasicSaving(age: Int): Int {
        var saving = 0

        when(age){
            16,17,18,19,20 -> saving = 5000
            21,22,23,24,25 -> saving = 14000
            26,27,28,29,30 -> saving = 29000
            31,32,33,34,35 -> saving = 50000
            36,37,38,39,40 -> saving = 78000
            41,42,43,44,45 -> saving = 116000
            46,47,48,49,50 -> saving = 165000
            51,52,53,54,55 -> saving = 228000
        }
        return saving
    }

    private fun calculateAge(year: Int): Int {
        return Calendar.getInstance().get(Calendar.YEAR) - year
    }
}
