package com.example.speakers.ui.screen.speakers.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.example.speakers.R
import com.example.speakers.data.model.EventSpeakersForSubdomain

@Composable
fun SpeakersList(
    list: List<EventSpeakersForSubdomain>,
    onSpeakerClicked: (EventSpeakersForSubdomain) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        itemsIndexed(list) { _, speaker ->
            Speaker(speaker, onSpeakerClicked)
        }
    }
}

@Composable
fun Speaker(
    speaker: EventSpeakersForSubdomain,
    onSpeakerClicked: (EventSpeakersForSubdomain) -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onSpeakerClicked.invoke(speaker)
        }) {

        val (speakerImageConstraint, socialMediaLinkConstraint, nameConstraint, speakerTypeConstraint, speakerProfessionConstraint, speakerCompanyConstraint, dividerConstraint) = createRefs()

        val painter = rememberAsyncImagePainter(speaker.speakerImagePath)

        Image(
            modifier = Modifier
                .width(130.dp)
                .height(120.dp)
                .constrainAs(speakerImageConstraint) {
                    top.linkTo(parent.top, 2.dp)
                    bottom.linkTo(parent.bottom, 2.dp)
                    start.linkTo(parent.start)
                }
                .clip(RoundedCornerShape(10.dp)),
            painter = painter,
            contentDescription = "speaker",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .padding(12.dp, 20.dp, 8.dp)
                .constrainAs(speakerTypeConstraint) {
                    top.linkTo(parent.top)
                    start.linkTo(speakerImageConstraint.end)
                    end.linkTo(socialMediaLinkConstraint.start)
                    width = Dimension.fillToConstraints
                },
            text = speaker.speakerTypeName ?: "",
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Medium
            ),
        )

        Row(modifier = Modifier.constrainAs(socialMediaLinkConstraint) {
            top.linkTo(parent.top, 10.dp)
            end.linkTo(parent.end)
        }) {
            val uriHandler = LocalUriHandler.current
            if (!speaker.speakerFacebookUrl.isNullOrEmpty()) SocialMediaIcon(R.drawable.ic_facebook) {
                uriHandler.openUri(speaker.speakerFacebookUrl!!)
            }
            if (!speaker.speakerInstagramUrl.isNullOrEmpty()) SocialMediaIcon(R.drawable.ic_instagram) {
                uriHandler.openUri(speaker.speakerInstagramUrl!!)
            }
            if (!speaker.speakerTwitterUrl.isNullOrEmpty()) SocialMediaIcon(R.drawable.ic_twitter) {
                uriHandler.openUri(speaker.speakerTwitterUrl!!)
            }
            if (!speaker.speakerLinkInUrl.isNullOrEmpty()) SocialMediaIcon(R.drawable.ic_linkedin) {
                uriHandler.openUri(speaker.speakerLinkInUrl!!)
            }
        }

        Text(
            modifier = Modifier
                .padding(12.dp, 2.dp, 8.dp)
                .constrainAs(nameConstraint) {
                    top.linkTo(speakerTypeConstraint.bottom)
                    start.linkTo(speakerImageConstraint.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = "${speaker.speakerFirstName} ${speaker.speakerLastName}",
            style = TextStyle(textAlign = TextAlign.Start, fontSize = 20.sp, color = Color.Black),
        )
        Text(
            modifier = Modifier
                .padding(12.dp, 1.dp, 8.dp)
                .constrainAs(speakerProfessionConstraint) {
                    top.linkTo(nameConstraint.bottom)
                    start.linkTo(speakerImageConstraint.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = speaker.speakerTitle ?: "",
            style = TextStyle(textAlign = TextAlign.Start, fontSize = 16.sp, color = Color.Black),
        )
        Text(
            modifier = Modifier
                .padding(12.dp, 1.dp, 8.dp)
                .constrainAs(speakerCompanyConstraint) {
                    top.linkTo(speakerProfessionConstraint.bottom)
                    start.linkTo(speakerImageConstraint.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            text = speaker.speakerCompanyName ?: "",
            style = TextStyle(
                textAlign = TextAlign.Start, fontSize = 10.sp, color = Color.Red,
                fontWeight = FontWeight.Medium
            ),
        )
        Divider(
            Modifier
                .height(1.dp)
                .background(Color(0xFFD9DDE3))
                .constrainAs(dividerConstraint) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }

}


@Composable
fun SocialMediaIcon(@DrawableRes iconRes: Int, onClick: () -> Unit) {
    val icon = rememberAsyncImagePainter(iconRes)
    Box(
        Modifier
            .padding(5.dp)
            .clickable {
                onClick.invoke()
            }) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = icon,
            contentDescription = "icon",
            contentScale = ContentScale.Crop
        )
    }
}
