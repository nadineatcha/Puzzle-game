package fr.uge.tp_examen_android

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.rememberCoroutineScope  // Ajout de cet import

@Composable
fun PuzzleFetcher(
    initialEmail: String = "",
    modifier: Modifier = Modifier,
    onNewPuzzle: (Puzzle, Int) -> Unit
) {
    var email by remember { mutableStateOf(initialEmail) }
    var difficulty by remember { mutableStateOf(3f) }
    var status by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !email.endsWith("@edu.univ-eiffel.fr") && !email.endsWith("@univ-eiffel.fr"),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Difficulty: ${difficulty.toInt()}")
        Slider(
            value = difficulty,
            onValueChange = { difficulty = it },
            valueRange = 2f..7f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    status = "fetching puzzle"
                    try {
                        val response = withContext(Dispatchers.IO) {
                            val url = URL(
                                "https://jigsaw.plade.org/puzzle/new?email=${
                                    URLEncoder.encode(
                                        email,
                                        "UTF-8"
                                    )
                                }&difficulty=${difficulty.toInt()}"
                            )
                            val connection = url.openConnection() as HttpURLConnection
                            connection.requestMethod = "GET"
                            try {
                                connection.inputStream.bufferedReader().use { it.readText() }
                            } catch (e: Exception) {
                                // Lire le message d'erreur si disponible
                                val errorMessage =
                                    connection.errorStream?.bufferedReader()?.use { it.readText() }
                                throw Exception("HTTP Error: ${connection.responseCode} - $errorMessage")
                            }
                        }

                        status = "Response received: $response" // Debug

                        val lines = response.split("\n")
                        if (lines.size >= 2) {
                            val puzzle = Puzzle(
                                id = lines[0].trim(),
                                name = lines[1].trim(),
                                email = email,
                                difficulty = difficulty.toInt()
                            )
                            status = "puzzle fetched: ${puzzle.name}"
                            onNewPuzzle(puzzle, difficulty.toInt())
                        } else {
                            status = "Invalid server response: $response"
                        }
                    } catch (e: Exception) {
                        status = "Error: ${e.message}"
                        e.printStackTrace()
                    }
                }
            },
            enabled = email.endsWith("@edu.univ-eiffel.fr") || email.endsWith("@univ-eiffel.fr"),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Puzzle")
        }
    }
}