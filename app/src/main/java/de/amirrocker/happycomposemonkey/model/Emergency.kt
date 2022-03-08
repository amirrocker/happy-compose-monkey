package de.amirrocker.happycomposemonkey.model

import androidx.compose.runtime.Immutable

@Immutable
data class Emergency(
    val courseId: Long,
    val name: String,
    val subject: String,
    val thumbUrl: String,
    val thumbContentDescription: String,
    val description: String,
    val steps: Int,
    val step: Int,
    val instructor: String = "Prof. Dr. Kermit"
)

/**
 * fake repo
 */
object CourseRepo {
    fun getCourse(courseId: Long): Emergency = courses.find { course -> course.courseId == courseId } ?: courses[0]
    fun getRelated(courseId: Long): List<Emergency> = courses
}

val courses = listOf(

    Emergency(
        courseId=0,
        name="Kurs A",
        subject = "Subject String0",
        thumbUrl = "ThumbUrl0",
        thumbContentDescription = "0",
        description = "Some description0",
        steps = 7,
        step = 1
    ),
    Emergency(
        courseId=1,
        name="nameString1",
        subject = "Subject String1",
        thumbUrl = "ThumbUrl1",
        thumbContentDescription = "",
        description = "Some description1",
        steps = 7,
        step = 1
    ),
    Emergency(
        courseId=2,
        name="nameString2",
        subject = "Subject String2",
        thumbUrl = "ThumbUrl2",
        thumbContentDescription = "2",
        description = "Some description2",
        steps = 7,
        step = 1
    ),
    Emergency(
        courseId=2,
        name="nameString2",
        subject = "Subject String2",
        thumbUrl = "ThumbUrl2",
        thumbContentDescription = "2",
        description = "Some description2",
        steps = 7,
        step = 1
    ),

)


