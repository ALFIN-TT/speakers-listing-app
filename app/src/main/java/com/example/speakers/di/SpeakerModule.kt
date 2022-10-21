package com.example.speakers.di

import com.example.speakers.common.network.BaseApi
import com.example.speakers.data.network.api.SpeakersApi
import com.example.speakers.data.repository.SpeakersRepository
import com.example.speakers.data.usecases.GetSpeakersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpeakerModule {

    @Provides
    @Singleton
    fun providesSpeakersApi(): SpeakersApi =
        BaseApi(SpeakersApi::class.java, "https://secure.eventtitans.com")


    @Provides
    @Singleton
    fun provideSpeakersRepository(speakersApi: SpeakersApi) = SpeakersRepository(speakersApi)

    @Provides
    fun provideSpeakersUseCase(speakersRepository: SpeakersRepository) =
        GetSpeakersUseCase(speakersRepository)

}