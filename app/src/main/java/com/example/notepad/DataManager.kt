package com.example.notepad

import android.icu.text.CaseMap

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init{
        initializedCourses()
        initializedNotes()
    }

    fun addNote(course:CourseInfo, noteTitle: String, noteText: String): Int{
        val note = NoteInfo(course, noteTitle,noteText)
        notes.add(note)
        return notes.lastIndex
    }

    private fun initializedCourses(){
        var course = CourseInfo("Android_Intents", "Android Programming with Intents")
        courses.set(course.courseId,course)

        course = CourseInfo("Android_Async", "Android Async Programming and Services")
        courses.set(course.courseId,course)

        course = CourseInfo("Java_Language", "Java Fundamentals: The Java Language")
        courses.set(course.courseId,course)

        course = CourseInfo("Java_Core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)

    }
    private fun initializedNotes(){
        var note = NoteInfo(courses["Android_Intents"] as CourseInfo, "Dynamic intent resolution", "Wow intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(courses["Android_Intents"] as CourseInfo, "Deleting intents", "PendingIntents are powerful, they delegate much more than just a component invocation")
        notes.add(note)

        note = NoteInfo(courses["Android_Async"] as CourseInfo, "Service default threads", "Did you know that by default an Android Service will tie up the UI thread")
        notes.add(note)

        note = NoteInfo(courses["Java_Language"] as CourseInfo, "Parameters", "Leverage variable-length parameters list")
        notes.add(note)

        note = NoteInfo(courses["Java_Language"] as CourseInfo, "Anonymous classes", "Anonymous classes simplify implementing one use type")
        notes.add(note)

        note = NoteInfo(courses["Java_Core"] as CourseInfo, "Compiler options", "The .jar options isn't compatible with the -cp option")
        notes.add(note)

        note = NoteInfo(courses["Java_Core"] as CourseInfo, "Serialization", "Remember to include Serial/VersionUID to assure version compatibility")
        notes.add(note)

    }

}