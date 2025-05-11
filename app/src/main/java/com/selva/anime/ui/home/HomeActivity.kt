package com.selva.anime.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.selva.anime.ui.detail.DetailActivity
import com.selva.anime.ui.home.theme.AnimeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewmodel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectNavigationEvent()
        setContent {
            AnimeTheme {
                HomeScreenRoute(viewmodel)
            }
        }
    }

    private fun collectNavigationEvent() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.navigationEvents.collectLatest { event ->
                    when (event) {
                        is HomeNavigationEvent.NavigateToAnimeDetails -> {
                            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                                putExtra(ANIME_ITEM, event.animeItem.id)
                            }.also {
                                startActivity(it)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val ANIME_ITEM = "anime_item"
    }
}