package fr.uge.tp_examen_android

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun RestartableSplashScreen() {
    var restartKey by remember { mutableStateOf(0) }

    SplashScreen(
        onClick = {
            restartKey++ // Force la recomposition
        }
    )
}