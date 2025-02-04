@Composable
fun SplashScreen(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(modifier = modifier.fillMaxSize()) {
        // Le drapeau November (pour Nadine)
        Box(modifier = Modifier.fillMaxSize()) {
            // Damier noir et blanc
            Column(modifier = Modifier.fillMaxSize()) {
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
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}