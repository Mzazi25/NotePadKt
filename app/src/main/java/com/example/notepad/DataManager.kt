package com.example.notepad

class DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init{
        initializedCourses()
    }

    private fun initializedCourses(){
        var course = CourseInfo("Android Intents", "Android Programming with Intents")
        courses.set(course.courseId,course)

        course = CourseInfo("Android Async", "Android Async Programming and Services")
        courses.set(course.courseId,course)

        course = CourseInfo("Java Language", "Java Fundamentals: The Java Language")
        courses.set(course.courseId,course)

        course = CourseInfo("Java Core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)

    }

}