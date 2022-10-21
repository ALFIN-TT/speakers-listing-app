package com.example.speakers.data.usecases

import com.example.speakers.common.network.ApiException
import com.example.speakers.common.network.Resource
import com.example.speakers.data.network.responses.success.SpeakersResponse
import com.example.speakers.data.repository.SpeakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpeakersUseCase @Inject constructor(private val speakersRepository: SpeakersRepository) {

    operator fun invoke(): Flow<Resource<SpeakersResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = speakersRepository.getSpeakers()
            emit(Resource.Success(data = response))
        } catch (e: ApiException) {
            emit(Resource.Error(e.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }
}