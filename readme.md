TP Android - Puzzle Game
Université Gustave Eiffel - 2024
Description
Ce projet est un jeu de puzzle développé avec Kotlin et Jetpack Compose pour Android. L'application permet aux étudiants de l'Université Gustave Eiffel de jouer à un jeu de puzzle où ils doivent reconstituer des images de lieux de l'université.
Fonctionnalités

Écran d'accueil (Splash Screen)

Affichage du drapeau maritime correspondant à l'initiale du prénom
Barre de progression animée
Bouton de démarrage


Configuration du Puzzle

Saisie de l'email universitaire (@univ-eiffel.fr ou @edu.univ-eiffel.fr)
Sélection de la difficulté (2-7)
Récupération du puzzle depuis le serveur


Jeu de Puzzle

Affichage des pièces du puzzle
Système d'échange de pièces
Validation de la solution
Maximum 3 tentatives de résolution



Technologies Utilisées

Kotlin
Jetpack Compose
Coroutines Kotlin
Material Design 3

Installation

Cloner le repository

bashCopygit clone [URL_du_repository]

Ouvrir le projet dans Android Studio
Synchroniser le projet avec les fichiers Gradle
Lancer l'application sur un émulateur ou un appareil physique

Structure du Projet
Copyapp/
├── src/
│   ├── main/
│   │   ├── java/fr/uge/tp_examen_android/
│   │   │   ├── MainActivity.kt
│   │   │   ├── SplashScreen.kt
│   │   │   ├── PuzzleFetcher.kt
│   │   │   ├── JigsawPiece.kt
│   │   │   ├── Jigsaw.kt
│   │   │   └── MainGame.kt
│   │   └── AndroidManifest.xml
│   └── build.gradle
Configuration Requise

Android Studio Arctic Fox ou plus récent
SDK Android minimum : API 24
Gradle 7.0 ou plus récent

Auteur:Nadine ATCHA