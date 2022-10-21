package com.example.speakers.ui.navigation

sealed class Screen(val route: String) {
    object SpeakersScreen : Screen("speakers_screen")
}