package com.example.speakers.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EventSpeakersForSubdomain(
    @SerializedName("CompanyContactId")
    var companyContactId: String?,
    @SerializedName("CompanyUserId")
    var companyUserId: String?,
    @SerializedName("EventSpeakerRelationshipId")
    var eventSpeakerRelationshipId: String?,
    @SerializedName("Id")
    var id: Int?,
    @SerializedName("IsSpeakerFavourite")
    var isSpeakerFavourite: Int?,
    @SerializedName("ProfileCode")
    var profileCode: String?,
    @SerializedName("ShowSpeakerInfo")
    var showSpeakerInfo: Boolean?,
    @SerializedName("SpeakerAboutMe")
    var speakerAboutMe: String?,
    @SerializedName("SpeakerCompanyName")
    var speakerCompanyName: String?,
    @SerializedName("SpeakerFacebookUrl")
    var speakerFacebookUrl: String?,
    @SerializedName("SpeakerFirstName")
    var speakerFirstName: String?,
    @SerializedName("SpeakerId")
    var speakerId: String?,
    @SerializedName("SpeakerImagePath")
    var speakerImagePath: String?,
    @SerializedName("SpeakerInstagramUrl")
    var speakerInstagramUrl: String?,
    @SerializedName("SpeakerLastName")
    var speakerLastName: String?,
    @SerializedName("SpeakerLinkInUrl")
    var speakerLinkInUrl: String?,
    @SerializedName("SpeakerRating")
    var speakerRating: Double?,
    @SerializedName("SpeakerTitle")
    var speakerTitle: String?,
    @SerializedName("SpeakerTwitterUrl")
    var speakerTwitterUrl: String?,
    @SerializedName("SpeakerType")
    var speakerType: Int?,
    @SerializedName("SpeakerTypeName")
    var speakerTypeName: String?
)