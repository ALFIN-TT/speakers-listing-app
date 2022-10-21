package com.example.speakers.ui.screen.speakers

import com.example.speakers.data.network.responses.success.SpeakersResponse

data class SpeakersState(
    var isLoading: Boolean = false,
    var data: SpeakersResponse? = null,
    var error: String = ""
)
