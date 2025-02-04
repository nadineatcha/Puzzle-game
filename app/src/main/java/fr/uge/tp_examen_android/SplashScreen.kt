package fr.uge.tp_examen_android

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    var progress by remember { mutableStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            progress = 0f
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(2000)
            ) { value, _ ->
                progress = value
            }
            isLoading = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Drapeau November (N pour Nadine)
        Column(modifier = Modifier.fillMaxSize()) {
            // Les 4 bandes du drapeau
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White))

            // Barre de progression ou bouton
            Box(modifier = Modifier.padding(16.dp)) {
                if (isLoading) {
                    FillBar(
                        fillRatio = progress,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Button(
                        onClick = {
                            isLoading = true
                            onClick()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Start")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}