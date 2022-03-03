package de.amirrocker.happycomposemonkey.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import de.amirrocker.happycomposemonkey.model.Course
import de.amirrocker.happycomposemonkey.model.courses
import de.amirrocker.happycomposemonkey.presentation.common.CourseListItem

@Composable
fun MyCourses(
    courses: List<Course>,
    selectCourse: (Long)->Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.statusBarsHeight())
        }
        item {
            CoursesAppBar()
        }
        itemsIndexed(courses) { index:Int, course:Course ->
            MyCourse(index, course, selectCourse)
        }
    }
}

@Composable
fun CoursesAppBar() {
    Row {
        Text(
            text="placeholder",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MyCourse(
    index:Int,
    course: Course,
    selectCourse: (Long) -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {

        val stagger = if(index % 2 == 0) {
            72.dp
        } else {
            16.dp
        }
        Spacer(modifier = Modifier.width(stagger))
        CourseListItem(
            course = course,
            clickCourse = { selectCourse(course.courseId) },
            modifier = Modifier.height(96.dp),
            shape = RoundedCornerShape(topStart = 24.dp),
        )
    }
}

@Preview
@Composable
fun MyCoursesPreview() {
    MyCourses(
        courses = courses,
        selectCourse = {}
    )
}


//@JvmInline
//value class SomeStringValue(val replaceMeAtCallSite: String)

