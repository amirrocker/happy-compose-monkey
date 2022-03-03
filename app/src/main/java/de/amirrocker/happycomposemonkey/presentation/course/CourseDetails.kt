package de.amirrocker.happycomposemonkey.presentation.course

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.Course
import de.amirrocker.happycomposemonkey.model.CourseRepo
import de.amirrocker.happycomposemonkey.model.Lesson
import de.amirrocker.happycomposemonkey.model.LessonsRepo
import de.amirrocker.happycomposemonkey.model.courses
import de.amirrocker.happycomposemonkey.presentation.utils.NetworkImage
import de.amirrocker.happycomposemonkey.presentation.utils.lerp
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme
import de.amirrocker.happycomposemonkey.ui.theme.Purple200
import de.amirrocker.happycomposemonkey.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun CourseDetails(
    courseId: Long,
    selectCourse: (Long) -> Unit,
    upPress: () -> Unit
) {
    // Fake Repo call
    // all data would come from a ViewModel/UseCase/Repository
    val course = remember(courseId) { CourseRepo.getCourse(courseId = courseId) }

    CourseDetails(
        course = course,
        selectCourse = selectCourse,
        upPress = upPress
    )
}

private val FabSize = 56.dp
private const val ExpandedSheetAlpha = 0.96f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseDetails(
    course: Course,
    selectCourse: (Long) -> Unit,
    upPress: () -> Unit
) {

    HappyComposeMonkeyTheme {

        BoxWithConstraints {
            val sheetState = rememberSwipeableState(initialValue = SheetState.Closed)
            val fabSize = with(LocalDensity.current) { FabSize.toPx() }
            val dragRange = constraints.maxHeight - fabSize
            val scope = rememberCoroutineScope()

            // close the bottom sheet
            BackHandler(
                enabled = sheetState.currentValue == SheetState.Open,
                onBack = {
                    scope.launch {
                        sheetState.animateTo(SheetState.Closed)
                    }
                }
            )

            Box(
                modifier = Modifier.swipeable(
                    state = sheetState,
                    anchors = mapOf(
                        0f to SheetState.Closed,
                        -dragRange to SheetState.Open
                    ),
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical
                )
            ) {
                val openFraction = if (sheetState.offset.value.isNaN()) {
                    0f
                } else {
                    println("sheetState: $sheetState")
                    -sheetState.offset.value / dragRange
                }.coerceIn(0f, 1f)

                LessonSheet(
                    course,
                    openFraction,
                    this@BoxWithConstraints.constraints.maxWidth.toFloat(),
                    this@BoxWithConstraints.constraints.maxHeight.toFloat(),
                ) { state ->
                    scope.launch {
                        sheetState.animateTo(state)
                    }
                }
            }
        }
    }
}

enum class SheetState { Open, Closed }

@Composable
fun LessonSheet(
    course: Course,
    openFraction: Float,
    width: Float,
    height: Float,
    updateSheet: (SheetState) -> Unit
) {

    // use the fraction that the sheet is open to drive the transformation from FAB -> Sheet
    val fabSize = with(LocalDensity.current) { FabSize.toPx() }
    println("fabSize: $fabSize")

    val fabSheetHeight = fabSize + LocalWindowInsets.current.systemBars.bottom
    println("fabSheetHeight: $fabSheetHeight")

    val offsetX = lerp(
        width - fabSize,
        0f,
        0f,
        0.15f,
        openFraction
    )
    val offsetY = lerp(height - fabSheetHeight, 0f, openFraction)
    val tlCorner = lerp(fabSize, 0f, 0f, 0.15f, openFraction)

    val surfaceColor = lerp(
        startColor = Purple200,
        endColor = MaterialTheme.colors.primarySurface.copy(alpha = ExpandedSheetAlpha),
        startFraction = 0f,
        endFraction = 0.3f,
        fraction = openFraction
    )
    // now create the surface
    Surface(
        color = surfaceColor,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primarySurface),
        shape = RoundedCornerShape(topStart = tlCorner),
        modifier = Modifier.graphicsLayer {
            translationX = offsetX
            translationY = offsetY
        }
    ) {
        Lessons(
            course,
            openFraction,
            surfaceColor,
            updateSheet
        )
    }
}

@Composable
fun Lessons(
    course: Course,
    openFraction: Float,
    surfaceColor:Color = MaterialTheme.colors.surface,
    updateSheet: (SheetState) -> Unit
) {
    val lessons:List<Lesson> = remember(course.courseId) { LessonsRepo.getLessons(course.courseId)}
    Box(modifier = Modifier.fillMaxWidth()) {
        //
        val lessonsAlpha = lerp(0f, 1f, 0.2f, 0.8f, openFraction)
        Column(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = lessonsAlpha
            }
            .statusBarsPadding()
        ) {
            val scroll = rememberLazyListState()
            val appBarElevation by animateDpAsState(targetValue = if (scroll.isScrolled) 4.dp else 0.dp)
            val appBarColor = if(appBarElevation > 0.dp) surfaceColor else Color.Transparent

            TopAppBar(
                backgroundColor = appBarColor,
                elevation = appBarElevation
            ) {

                Text(
                    text=course.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                IconButton(
                    onClick = { updateSheet(SheetState.Closed) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ExpandMore,
                        contentDescription = stringResource(id = R.string.collapse_bottom_sheet)
                    )
                }
            }
            LazyColumn(
                state = scroll,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    applyTop = false
                )
            ) {
                items(lessons) { lesson ->
                    Lesson(lesson)
                    Divider(startIndent = 128.dp)
                }
            }
        }
    }
}

@Composable
fun Lesson(lesson:Lesson) {
    Row(
        modifier = Modifier
            .clickable { println(" A lesson $lesson has been clicked - open a snackbar or something ... ") }
            .padding(8.dp)
    ) {
        NetworkImage(url = lesson.imageUrl,
            contentDescription = "",
            modifier = Modifier.size(112.dp, 64.dp))
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp)
        ) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


private val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@Preview
@Composable
fun LessonSheetPreview() {
    LessonSheet(
        course = courses.first(),
        openFraction = 0.3f,
        1.0f,
        1.0f
    ) { sheetState -> println("sheetState: $sheetState") }
}

