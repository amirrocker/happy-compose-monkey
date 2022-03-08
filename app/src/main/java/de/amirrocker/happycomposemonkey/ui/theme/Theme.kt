package de.amirrocker.happycomposemonkey.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import de.amirrocker.happycomposemonkey.R

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


private val LightElevation = Elevation()

private val DarkElevation = Elevation(card = 1.dp)

private val LightImages = Images(R.drawable.ic_launcher_background)

private val DarkImages = Images(R.drawable.ic_launcher_background)

@Composable
fun HappyComposeMonkeyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val elevation = if(darkTheme) DarkElevation else LightElevation
    val images = if(darkTheme) DarkImages else LightImages
    CompositionLocalProvider(
        LocalElevations provides elevation,
        localImages provides images
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

/**
 * Alternate to Theme [MaterialTheme] to enable adding own theme systems such as
 * [Elevation] and / or extend types such [Color]
 */
object HappyComposeMonkeyTheme {

    /* a proxy to [MaterialTheme] Colors */
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    /**
     * Retrieves the current [Elevation] at the call site's position in the hierarchy.
     * TODO Look into this more
     */
    val elevation : Elevation
        @Composable
        get() = LocalElevations.current
}


