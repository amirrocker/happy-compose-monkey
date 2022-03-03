package de.amirrocker.happycomposemonkey.presentation.courses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.Course
import de.amirrocker.happycomposemonkey.model.courses
import de.amirrocker.happycomposemonkey.presentation.CoursesAppBar
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme

@Composable
fun FeaturedCourses(
    courses: List<Course>,
    selectCourse: (Long)->Unit,
    modifier:Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .statusBarsPadding()
    ) {
        item {
            CoursesAppBar()
        }
        items(courses) { course ->
            FeaturedCourse(
                course = course,
                selectCourse = selectCourse
            )
        }
    }
}

@Composable
fun FeaturedCourse(
    course: Course,
    selectCourse: (Long)->Unit,
    modifier:Modifier = Modifier
) {

    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = HappyComposeMonkeyTheme.elevations.card,
        shape = MaterialTheme.shapes.medium
    ) {
        val featuredString = stringResource(id = R.string.value_d)

        ConstraintLayout(
            modifier = Modifier
                .clickable {
                    selectCourse(course.courseId)
                }
                .semantics {
                    contentDescription = featuredString
                }
        )
        {
            val (name) = createRefs()
            Text(
                text=course.name,
                style = MaterialTheme.typography.subtitle1,
                modifier= modifier
                    .padding(8.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
        }

    }
}

@Preview
@Composable
fun FeaturedCoursePreview() {
    HappyComposeMonkeyTheme {
        FeaturedCourse(
            course = courses.first(),
            selectCourse = { }
        )
    }
}

@Preview
@Composable
fun FeaturedCoursesPreview() {
    FeaturedCourses(
        courses = courses,
        selectCourse = {} )
}


