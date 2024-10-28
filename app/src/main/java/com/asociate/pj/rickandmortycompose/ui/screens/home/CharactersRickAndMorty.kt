package com.asociate.pj.rickandmortycompose.ui.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.asociate.pj.rickandmortycompose.ui.data.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersRickAndMorty() {
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = {
                // TopAppBar(title = { Text(text = "Rick and Morty") })
                TopAppBar(
                    title = { Text(text = "Rick and Morty") },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { paddingValues ->
            // Content of the screen
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = paddingValues,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                items(20) {
                    // CharacterItem(character = it) { onCharacterClick(it) }
                    CharacterItem(
                        character = CharacterModelRickAndMorty(
                            name = "Rick",
                            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                        )
                    ) { Log.e("CharactersRickAndMorty", "Character clicked")}
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModelRickAndMorty,onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick =  onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .clip(CircleShape)
        )
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}