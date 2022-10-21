package com.example.speakers.ui.screen.speakers.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.speakers.data.model.EventSpeakersForSubdomain

@Composable
fun SpeakerDialog(
    modifier: Modifier = Modifier,
    speaker: EventSpeakersForSubdomain,
    onDismiss: () -> Unit,
) {
    val painter = rememberAsyncImagePainter(speaker.speakerImagePath)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painter,
                    contentDescription = speaker.speakerFirstName
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    text = "${speaker.speakerFirstName} ${speaker.speakerLastName}",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}