package com.example.notepad

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

internal class DataManagerTest {
    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.initializedNotes()
    }

    @Test
    fun addNote() {
        val course = DataManager.courses["Android_Async"]!!
        val title = "This is a test title"
        val text = "This is a text note"

        val index = DataManager.addNote(course,title,text)
        val note = DataManager.notes[index]
        assertEquals(course,note.course)
        assertEquals(title,note.title)
        assertEquals(text,note.text)
    }
}