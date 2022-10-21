package com.example.speakers.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Speaker(
    @SerializedName("EventSpeakersForSubdomains")
    var eventSpeakersForSubdomains: List<EventSpeakersForSubdomain>?,
    @SerializedName("ShowSpeakerInfo")
    var showSpeakerInfo: Any?,
    @SerializedName("SpeakerType")
    var speakerType: String?,
    @SerializedName("SpeakerTypeId")
    var speakerTypeId: String?
)