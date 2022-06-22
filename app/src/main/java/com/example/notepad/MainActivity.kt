package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(){
    private lateinit var spinnerCourses: Spinner
    private lateinit var textNoteTitle : TextView
    private lateinit var textNoteText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCourses = findViewById(R.id.spinnerCourses)
        textNoteTitle = findViewById(R.id.textNoteTitle)
        textNoteText = findViewById(R.id.textNoteText)

        val dm = DataManager()
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
        android.R.layout.simple_spinner_item,dm.courses.values.toList())

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true

    }
}