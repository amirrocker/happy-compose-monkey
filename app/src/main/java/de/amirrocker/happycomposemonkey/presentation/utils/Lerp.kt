package de.amirrocker.happycomposemonkey.presentation.utils

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp as colorLerp


fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float
): Float = startValue + fraction * (endValue - startValue)

/**
 * Linear interpolation between two Floats when the fraction is in a given range
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, to = 1.0) startFraction: Float,
    @FloatRange(from = 0.0, to = 1.0) endFraction: Float,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float,
): Float = if(fraction < startFraction) {
        startValue
    } else if(fraction > endFraction) {
        endValue
    } else {
        lerp(
            startValue = startValue,
            endValue = endValue,
            fraction = (fraction - startFraction) / (endFraction - startFraction)
        )
    }

/**
 * Linear interpolation between two colors
 */
fun lerp(
    startColor: Color,
    endColor: Color,
    @FloatRange(from = 0.0, to=1.0) startFraction: Float,
    @FloatRange(from = 0.0, to=1.0) endFraction: Float,
    @FloatRange(from = 0.0, to=1.0) fraction: Float,
): Color = if(fraction < startFraction) {
    startColor
} else if(fraction > endFraction) {
    endColor
} else {
    colorLerp(
        startColor,
        endColor,
        (fraction - startFraction) / (endFraction - startFraction)
    )
}




/*
// alt. body:
//    if (fraction < startFraction) return startValue
//    if (fraction > endFraction) return endValue
//
//    return lerp(
//        startValue = startValue,
//        endValue = endValue,
//        fraction = (fraction - startFraction) / (endFraction - startFraction)
//    )
*/



