package fr.uge.tp_examen_android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

@Composable
fun JigsawPiece(
    puzzleId: String,
    pieceRank: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            model = "https://jigsaw.plade.org/puzzle/$puzzleId/$pieceRank",
            contentDescription = "Puzzle piece $pieceRank",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    Text(
                        text = "...",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                ) {
                    Text(
                        text = "KO",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun JigsawPiecePreview() {
    JigsawPiece(
        puzzleId = "test",
        pieceRank = 0,
        onClick = {}
    )
}