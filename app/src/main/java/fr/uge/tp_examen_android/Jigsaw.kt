package fr.uge.tp_examen_android

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Jigsaw(
    puzzleId: String,
    permutation: IntArray,
    modifier: Modifier = Modifier,
    selectedPiece1: Int? = null,    // Pour la bordure rouge
    selectedPiece2: Int? = null,    // Pour la bordure bleue
    onClick: (Int) -> Unit
) {
    val size = permutation.size
    val gridSize = kotlin.math.sqrt(size.toFloat()).toInt()

    Column(modifier = modifier) {
        for (row in 0 until gridSize) {
            Row {
                for (col in 0 until gridSize) {
                    val position = row * gridSize + col
                    val pieceModifier = Modifier
                        .weight(1f)
                        .padding(1.dp)
                        .then(
                            when (position) {
                                selectedPiece1 -> Modifier.border(2.dp, Color.Red)
                                selectedPiece2 -> Modifier.border(2.dp, Color.Blue)
                                else -> Modifier
                            }
                        )

                    JigsawPiece(
                        puzzleId = puzzleId,
                        pieceRank = permutation[position],
                        modifier = pieceModifier,
                        onClick = { onClick(position) }
                    )
                }
            }
        }
    }
}

@Composable
fun JigsawManager(
    puzzleId: String,
    difficulty: Int,
    modifier: Modifier = Modifier,
    onSubmit: (IntArray) -> Unit
) {
    var permutation by remember { mutableStateOf(IntArray(difficulty * difficulty) { it }) }
    var selectedPosition1 by remember { mutableStateOf<Int?>(null) }
    var selectedPosition2 by remember { mutableStateOf<Int?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Jigsaw(
            puzzleId = puzzleId,
            permutation = permutation,
            selectedPiece1 = selectedPosition1,
            selectedPiece2 = selectedPosition2,
            modifier = Modifier.weight(1f),
            onClick = { clickedPosition ->
                when {
                    selectedPosition1 == null -> {
                        selectedPosition1 = clickedPosition
                    }
                    selectedPosition2 == null -> {
                        selectedPosition2 = clickedPosition
                        // Lance l'échange après 500ms
                        coroutineScope.launch {
                            delay(500)
                            permutation = permutation.clone().also {
                                val temp = it[selectedPosition1!!]
                                it[selectedPosition1!!] = it[selectedPosition2!!]
                                it[selectedPosition2!!] = temp
                            }
                            selectedPosition1 = null
                            selectedPosition2 = null
                        }
                    }
                }
            }
        )

        Button(
            onClick = { onSubmit(permutation) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Submit")
        }
    }
}

@Preview
@Composable
fun JigsawPreview() {
    JigsawManager(
        puzzleId = "test",
        difficulty = 3,
        onSubmit = {}
    )
}