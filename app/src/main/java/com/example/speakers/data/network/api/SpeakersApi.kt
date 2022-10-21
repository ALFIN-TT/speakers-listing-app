package com.example.speakers.data.network.api

import com.example.speakers.data.network.responses.success.SpeakersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpeakersApi {

    @GET("/api/master/geteventspeakersforsubdomain")
    suspend fun getSpeakers(
        @Query("EventId", encoded = true) eventId: String = "rzlzYoQDCK56RP2FY7TD-w%3D%3D",
        @Query(
            "UserLoginId",
            encoded = true
        ) userLoginId: String = "iTMYej2or0Qd1%2FAtMo3j3w%3D%3D",
    ): Response<SpeakersResponse>

}