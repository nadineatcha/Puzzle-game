package fr.uge.tp_examen_android

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import kotlinx.coroutines.launch
import java.net.URL
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConnectedJigsaw(
    puzzleId: String,
    difficulty: Int,
    modifier: Modifier = Modifier,
    onResult: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var submissionStatus by remember { mutableStateOf<String?>(null) }

    JigsawManager(
        puzzleId = puzzleId,
        difficulty = difficulty,
        modifier = modifier,
        onSubmit = { permutation ->
            coroutineScope.launch {
                try {
                    // Convertit la permutation en chaîne pour l'URL
                    val permutationString = permutation.joinToString(",")
                    val url = "https://jigsaw.plade.org/puzzle/$puzzleId/submit?permutation=$permutationString"

                    // Fait la requête au serveur
                    val response = URL(url).readText().trim()

                    // Vérifie le résultat
                    val isCorrect = response == "OK"
                    submissionStatus = if (isCorrect) "Correct!" else "Incorrect, try again!"
                    onResult(isCorrect)

                } catch (e: Exception) {
                    submissionStatus = "Error checking solution: ${e.message}"
                    onResult(false)
                }
            }
        }
    )

    // Affiche le statut de la soumission si disponible
    submissionStatus?.let { status ->
        Text(
            text = status,
            modifier = Modifier.padding(16.dp),
            color = if (status.startsWith("Correct")) Color.Green else Color.Red
        )
    }
}

@Preview
@Composable
fun ConnectedJigsawPreview() {
    ConnectedJigsaw(
        puzzleId = "test",
        difficulty = 3,
        onResult = {}
    )
}