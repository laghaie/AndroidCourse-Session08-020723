package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var selectedCourse: String? = null
        val spinner = findViewById<Spinner>(R.id.spinner)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val txvCourseResult = findViewById<TextView>(R.id.txvCourseResult)

        val courseName = listOf(
            "Android Application Development",
            "Android Security Essentials",
            "Android UI/UX Design"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseName)
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(R.layout.spinner_list)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                positon: Int,
                p3: Long
            ) {
                selectedCourse = parent?.getItemAtPosition(positon).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        btnSubmit.setOnClickListener {
            txvCourseResult.text = selectedCourse
        }
    }
}