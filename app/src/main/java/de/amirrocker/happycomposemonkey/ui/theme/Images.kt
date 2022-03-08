package de.amirrocker.happycomposemonkey.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Images that vary by theme
 */
@Immutable
data class Images(
    @DrawableRes val imageRes: Int
)

val localImages = staticCompositionLocalOf<Images> {
    error("No image defined")
}