package de.amirrocker.happycomposemonkey.model

import androidx.compose.runtime.Immutable

@Immutable
data class Topic(
    val name: String,
    val numberOfCourses: Int,
    val imageUrl: String
)

object TopicRepo {
    fun getTopics():List<Topic> = topics
}


private val topics = listOf(
    Topic(
        name="Topic A",
        numberOfCourses = 23,
        imageUrl = "Topic A Description"
    ),
    Topic(
        name="Topic B",
        numberOfCourses = 23,
        imageUrl = "Topic B Description"
    ),
    Topic(
        name="Topic C",
        numberOfCourses = 23,
        imageUrl = "Topic C Description"
    ),
    Topic(
        name="Topic D",
        numberOfCourses = 23,
        imageUrl = "Topic D Description"
    ),
    Topic(
        name="Topic E",
        numberOfCourses = 23,
        imageUrl = "Topic E Description"
    ),
    Topic(
        name="Topic F",
        numberOfCourses = 23,
        imageUrl = "Topic F Description"
    ),
    Topic(
        name="Topic G",
        numberOfCourses = 23,
        imageUrl = "Topic G Description"
    ),
)