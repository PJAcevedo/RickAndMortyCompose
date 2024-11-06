package com.asociate.pj.rickandmortycompose.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.asociate.pj.rickandmortycompose.ui.data.entity.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.screens.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersRickAndMorty(
    viewModel: CharactersRickAndMortyViewModel = viewModel(),
    onCharacterDetailClick: (CharacterModelRickAndMorty) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    LaunchedEffect(Unit) {
        viewModel.onUiReady()
    }
    Screen {
        Scaffold(
            topBar = {
                // TopAppBar(title = { Text(text = "Rick and Morty") })
                TopAppBar(
                    title = { Text(text = "Rick and Morty") },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { paddingValues ->
            val state = viewModel.state
            if (state.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = paddingValues,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                items(state.characters) {
                    CharacterItem(it) { onCharacterDetailClick(it) }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModelRickAndMorty, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick =  onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}