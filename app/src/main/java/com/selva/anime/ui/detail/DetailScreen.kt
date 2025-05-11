package com.selva.anime.ui.detail

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.selva.anime.domain.model.AnimeInfoItem
import com.selva.anime.domain.model.CharacterItem
import com.selva.anime.ui.common.LoadingScreen

@Composable
fun DetailScreenRoute(
    viewModel: DetailViewModel,
    intent: Intent?
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchRemoteData(intent)
    }

    when (val currentState = state.detailState) {
        is DetailContract.DetailState.Error -> {
            Text(text = currentState.message)
            Log.e("selva", currentState.message)
        }

        DetailContract.DetailState.Idle -> {
            Text(text = "welcome to detail screen")
        }

        DetailContract.DetailState.Loading -> {
            LoadingScreen()
        }

        is DetailContract.DetailState.Success -> {
            DetailScreen(currentState.info.animeInfo, currentState.info.character)
        }
    }
}

@Composable
fun DetailScreen(
    info: AnimeInfoItem,
    character: List<CharacterItem>
) {
    val columnScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(columnScrollState)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (!info.youtubeId.isNullOrBlank()) {
            AndroidView(
                factory = { context ->
                    YouTubePlayerView(context).apply {
                        (context as? ComponentActivity)?.lifecycle?.addObserver(this)
                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(info.youtubeId, 0f)
                            }
                        })
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        } else if (!info.image.isNullOrBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(info.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "${info.title} Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = info.plot,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Genres: ${info.genre?.joinToString(", ")}",
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Main Cast: ${character.joinToString(", ") { it.name }}",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Episodes: ${info.episode}",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Rating: ${info.rating}",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }
}