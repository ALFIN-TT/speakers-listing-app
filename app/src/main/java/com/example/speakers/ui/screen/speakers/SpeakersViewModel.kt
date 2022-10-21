package com.example.speakers.ui.screen.speakers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speakers.common.network.Resource
import com.example.speakers.data.usecases.GetSpeakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SpeakersViewModel @Inject constructor(val useCase: GetSpeakersUseCase) : ViewModel() {

    private val _speakers = mutableStateOf(SpeakersState())
    val speakers: State<SpeakersState> = _speakers

    private fun getSpeakers() {
        useCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _speakers.value = SpeakersState(isLoading = true)
                }
                is Resource.Success -> {
                    _speakers.value = SpeakersState(data = it.data)
                }
                is Resource.Error -> {
                    _speakers.value = SpeakersState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getSpeakers()
    }
}