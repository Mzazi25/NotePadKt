package com.example.notepad

data class CourseInfo(val courseId: String, val title: String){
    override fun toString(): String {
        return super.toString()
    }
    }

data class NoteInfo(var course: CourseInfo, var title: String, var text: String)