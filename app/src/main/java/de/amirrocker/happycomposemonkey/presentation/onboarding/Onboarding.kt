package de.amirrocker.happycomposemonkey.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Onboarding( onboardingComplete: () -> Unit ) {
    OnboardingContent(onboardingComplete = onboardingComplete)
}

@Composable
fun OnboardingContent(onboardingComplete: () -> Unit) {

    Surface(
        modifier = Modifier.padding(start=8.dp, top=64.dp, end=8.dp, bottom=8.dp)
    ) {
        Column {
            Text(text = "Onboarding Content")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Beschreibung Onboarding")
            Button(onClick = {
                onboardingComplete()
            }) {
                Text(text="Go on ....")
            }
        }
    }
}










