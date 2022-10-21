package com.example.speakers.data.repository

import com.example.speakers.common.network.SafeApiRequest
import com.example.speakers.data.network.api.SpeakersApi
import com.example.speakers.data.network.responses.success.SpeakersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SpeakersRepository @Inject constructor(
    private val speakersApi: SpeakersApi
) : SafeApiRequest() {


    suspend fun getSpeakers(): SpeakersResponse {
        return withContext(Dispatchers.IO) {
            val response = safeApiRequest { speakersApi.getSpeakers() }
            response
        }
    }
}