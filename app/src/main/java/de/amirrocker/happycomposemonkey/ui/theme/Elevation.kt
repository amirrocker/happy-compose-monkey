package de.amirrocker.happycomposemonkey.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/* alt. Theme */
@Immutable
data class Elevation(val card: Dp = 0.dp)

//internal val LocalElevations = compositionLocalOf { Elevation() }
internal val LocalElevations = staticCompositionLocalOf { Elevation() }

val MaterialTheme.elevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevations.current