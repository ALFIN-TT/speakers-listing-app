package com.example.speakers.ui.screen.speakers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.speakers.data.model.EventSpeakersForSubdomain
import com.example.speakers.ui.screen.speakers.composable.SpeakerDialog
import com.example.speakers.ui.screen.speakers.composable.SpeakersList

@Composable
fun SpeakersScreen(
    navHostController: NavHostController,
    viewModel: SpeakersViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {

    val speakerTypeIcons by lazy {
        //dummy icons
        arrayListOf(
            Pair("Keynote Speakers", Icons.Filled.Edit),
            Pair("Guest Speakers", Icons.Filled.Person),
            Pair("Speakers", Icons.Filled.Menu),
            Pair("Presenters", Icons.Filled.PlayArrow),
            Pair("Host Speakers", Icons.Filled.Place),
            Pair("Tests", Icons.Filled.Create),
            Pair("samples", Icons.Filled.Star)
        )
    }

    val resource = viewModel.speakers.value
    if (resource.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (resource.error.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = resource.error,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color.Black
                )
            )
        }
    } else if (!resource.data.isNullOrEmpty()) {

        val isOpenDialog = remember { mutableStateOf(false) }
        val selectedSpeaker = remember { mutableStateOf<EventSpeakersForSubdomain?>(null) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopAppBar(
                modifier = Modifier
                    .height(150.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF370B94),
                                Color(0xFFC30A78),
                                Color(0xFFE70A70)
                            )
                        )
                    ),
                title = {
                    Text(
                        text = "Speakers",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                            color = Color.White
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )

            val items = resource.data?.map { it.speakerType } ?: emptyList()
            var selectedItem by remember { mutableStateOf(items[0]) }

            LazyRow(horizontalArrangement = Arrangement.Center) {
                this.items(items = items) {
                    Row(modifier = Modifier
                        .selectable(
                            selected = selectedItem == it,
                            onClick = { selectedItem = it }
                        )
                        .padding(horizontal = 6.dp, vertical = 12.dp)
                    ) {
                        val imageVector = speakerTypeIcons.find { type -> type.first == it }
                        if (selectedItem == it) {
                            Button(
                                onClick = {
                                    selectedItem = it
                                },
                                shape = RoundedCornerShape(100.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color(0xff00ACEA)
                                )
                            ) {
                                Icon(
                                    imageVector = imageVector?.second ?: Icons.Filled.AddCircle,
                                    contentDescription = "icon",
                                    tint = Color.White
                                )
                                Text(
                                    text = it ?: "",
                                    fontSize = 17.sp,
                                    modifier = Modifier.padding(horizontal = 1.dp, vertical = 1.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                selectedItem = it
                            }) {
                                Icon(
                                    imageVector = imageVector?.second ?: Icons.Filled.AddCircle,
                                    contentDescription = "icon",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            Divider(
                Modifier
                    .height(1.dp)
                    .background(Color(0xFFD9DDE3))
            )

            val speakers = resource.data?.find { it.speakerType == selectedItem }
            if (speakers != null) {
                if (!speakers.eventSpeakersForSubdomains.isNullOrEmpty()) {
                    SpeakersList(list = speakers.eventSpeakersForSubdomains!!) {
                        selectedSpeaker.value = it
                        isOpenDialog.value = true
                    }
                } else {
                    EmptyScreen()
                }
            } else {
                EmptyScreen()
            }
        }
        if (isOpenDialog.value) {
            selectedSpeaker.value?.let {
                SpeakerDialog(
                    modifier = Modifier
                        .fillMaxWidth(),
                    speaker = it,
                    onDismiss = {
                        isOpenDialog.value = false
                        selectedSpeaker.value = null
                    }
                )
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            text = "No Speakers Found",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                color = Color.Black
            )
        )
    }
}