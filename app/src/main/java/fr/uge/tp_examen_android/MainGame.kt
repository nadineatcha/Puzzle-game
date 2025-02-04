package fr.uge.tp_examen_android

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class GameState {
    object Splash : GameState()
    object FetchPuzzle : GameState()
    data class Playing(val puzzle: Puzzle) : GameState()
    data class Finished(val success: Boolean) : GameState()
}

@Composable
fun MainGame() {
    var gameState by remember { mutableStateOf<GameState>(GameState.Splash) }
    var lastEmail by remember { mutableStateOf("") }
    var attemptCount by remember { mutableStateOf(0) }
    val context = LocalContext.current

    when (val currentState = gameState) {
        is GameState.Splash -> {
            SplashScreen(
                onClick = {
                    gameState = GameState.FetchPuzzle
                }
            )
        }

        is GameState.FetchPuzzle -> {
            PuzzleFetcher(
                initialEmail = lastEmail,
                onNewPuzzle = { puzzle, _ ->
                    lastEmail = puzzle.email
                    attemptCount = 0
                    gameState = GameState.Playing(puzzle)
                }
            )
        }

        is GameState.Playing -> {
            ConnectedJigsaw(
                puzzleId = currentState.puzzle.id,
                difficulty = currentState.puzzle.difficulty,
                onResult = { success ->
                    if (success) {
                        gameState = GameState.Finished(true)
                    } else {
                        attemptCount++
                        if (attemptCount >= 3) {
                            gameState = GameState.Finished(false)
                        } else {
                            Toast.makeText(
                                context,
                                "Incorrect solution! Attempts left: ${3 - attemptCount}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            )
        }

        is GameState.Finished -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (currentState.success)
                        "Congratulations! You solved the puzzle!"
                    else
                        "Game Over! You've used all your attempts.",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        gameState = GameState.FetchPuzzle
                    }
                ) {
                    Text("Play Again")
                }
            }
        }

        GameState.FetchPuzzle -> TODO()
        is GameState.Finished -> TODO()
        is GameState.Playing -> TODO()
        GameState.Splash -> TODO()
    }
}

@Preview
@Composable
fun MainGamePreview() {
    MaterialTheme {
        MainGame()
    }
}