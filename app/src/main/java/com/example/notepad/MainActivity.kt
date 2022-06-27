package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private var notePosition = POSITION_NOTE_SET
    private lateinit var spinnerCourses: Spinner
    private lateinit var textNoteTitle : TextView
    private lateinit var textNoteText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCourses = findViewById(R.id.spinnerCourses)
        textNoteTitle = findViewById(R.id.textNoteTitle)
        textNoteText = findViewById(R.id.textNoteText)

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
        android.R.layout.simple_spinner_item,DataManager.courses.values.toList())

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOTE_SET)?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOTE_SET)
        if(notePosition != POSITION_NOTE_SET){
            displayNote()
        }else{
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex

        }
        Log.d(TAG, "On Create")
    }

    private fun displayNote() {
        Log.i(TAG, "Display Note $notePosition")
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION,notePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_next ->{
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if(menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_block)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onPause() {
        super.onPause()
        saveNote()
        Log.d(TAG, "On Pause")
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
}