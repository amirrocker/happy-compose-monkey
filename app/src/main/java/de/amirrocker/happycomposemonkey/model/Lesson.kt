package de.amirrocker.happycomposemonkey.model

import androidx.compose.runtime.Immutable

@Immutable
data class Lesson(
    val id:Long,
    val title: String,
    val formattedStepNumber: String,
    val length: String,
    val imageUrl: String,
    val imageContentDescription: String = ""
)

object LessonsRepo {
    fun getLessons() = lessons
    fun getLessons(courseId: Long) = lessons
}

val lessons = listOf(
    Lesson(
        0,
        "Lesson 1",
        "01",
        "12:12",
        "https://images.unsplash.com/photo-1511715282680-fbf93a50e721",
    ),
    Lesson(
        1,
        "Lesson 1",
        "01",
        "12:12",
        "https://images.unsplash.com/photo-1511715282680-fbf93a50e721",
    ),
    Lesson(
        2,
        "Lesson 1",
        "01",
        "12:12",
        "https://images.unsplash.com/photo-1511715282680-fbf93a50e721",
    ),
)