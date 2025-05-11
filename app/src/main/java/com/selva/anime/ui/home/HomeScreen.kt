package com.selva.anime.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.selva.anime.R
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.ui.common.LoadingScreen

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchTopAnime()
    }

    when (val currentState = state.animeState) {
        HomeContract.AnimeState.Idle -> {
            Text(text = "welcome to Anime app")
        }

        HomeContract.AnimeState.Loading -> {
            LoadingScreen()
        }

        is HomeContract.AnimeState.Success -> {
            TopAnime(
                animeItemList = currentState.animeList,
                onItemClick = viewModel::handleEvent
            )
        }

        is HomeContract.AnimeState.Error -> {
            Toast.makeText(LocalContext.current, currentState.message, Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAnime(
    animeItemList: List<AnimeItem>,
    onItemClick: (HomeContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.top_bar_title),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            itemsIndexed(animeItemList) { index, item ->
                AnimeItemLayout(item, onItemClick)
            }
        }
    }
}

@Composable
private fun AnimeItemLayout(
    item: AnimeItem,
    onItemClick: (HomeContract.Event) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        onClick = {
            onItemClick(HomeContract.Event.OnAnimeSelected(item))
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .size(height = 150.dp, width = 120.dp),
                painter = rememberAsyncImagePainter(model = item.imageUrl),
                contentDescription = item.title
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Title: ${item.title}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Episode: ${item.episode}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rating: ${item.rating}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopAnime() {
    val item = listOf(
        AnimeItem(id = 1, title = "a", imageUrl = "", episode = "1", rating = "", ),
        AnimeItem(id = 1, title = "b", imageUrl = "", episode = "1", rating = "", ),
        AnimeItem(id = 1, title = "c", imageUrl = "", episode = "1", rating = "", ),
        AnimeItem(id = 1, title = "d", imageUrl = "", episode = "1", rating = "", ),
        AnimeItem(id = 1, title = "e", imageUrl = "", episode = "1", rating = "", ),
        AnimeItem(id = 1, title = "f", imageUrl = "", episode = "1", rating = "", ),
    )
    TopAnime(item) {}
}