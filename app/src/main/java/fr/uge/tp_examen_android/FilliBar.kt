
package fr.uge.tp_examen_android
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FillBar(fillRatio: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(30.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Color.Black)
    ) {
        // Partie remplie
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fillRatio)
                .background(Color.Black)
        )

        // Texte du pourcentage
        Text(
            text = "${(fillRatio * 100).toInt()}%",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun FillBarPreview() {
    Column {
        FillBar(fillRatio = 0f)
        FillBar(fillRatio = 0.5f)
        FillBar(fillRatio = 1f)
    }
}