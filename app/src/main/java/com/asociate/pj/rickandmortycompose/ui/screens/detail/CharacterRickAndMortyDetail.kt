package com.asociate.pj.rickandmortycompose.ui.screens.detail

import android.widget.EditText
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.asociate.pj.rickandmortycompose.R
import com.asociate.pj.rickandmortycompose.ui.data.entity.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(
    viewModel: CharacterDetailRickAndMortyViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.state
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color(0xFFa6cccc) // or any other color
    )
    Screen {
        Scaffold(
            containerColor = Color(0xFFa6cccc),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = topAppBarColors,
                    title = { Text(text = state.character?.name.orEmpty(), fontFamily = FontFamily.Monospace)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
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
                state.character?.let {
                    AsyncImage(
                        model = it.image,
                        contentDescription = it.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )

                    Text(
                        text = it.origin.name,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Monospace
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Location(character = it)
                        Specie(character = it)
                        Gender(character = it)

                    }
                }
            }
        }
    }
}

@Composable
fun Location(character: CharacterModelRickAndMorty) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Location: ",
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = character.location.name,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun Specie(character: CharacterModelRickAndMorty) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Specie: ",
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = character.species,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun Gender(character: CharacterModelRickAndMorty) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Gender: ",
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = character.gender,
            fontFamily = FontFamily.Monospace
        )
    }
}