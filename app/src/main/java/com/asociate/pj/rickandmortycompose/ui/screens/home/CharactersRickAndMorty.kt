package com.asociate.pj.rickandmortycompose.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    /*val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF6b7132), Color(0xFFa6cccc)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }*/
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color(0xFFa6cccc) // or any other color
    )
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    LaunchedEffect(Unit) {
        viewModel.onUiReady()
    }
    Screen {
        Scaffold(
            containerColor = Color(0xFFa6cccc),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = topAppBarColors,
                    title = {
                        Text(
                            text = "Rick and Morty",
                            fontFamily = FontFamily.Monospace
                        )
                    },
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
                columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = paddingValues,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {
                items(state.characters) {
                    CharacterItem(it) { onCharacterDetailClick(it) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(character: CharacterModelRickAndMorty, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick =  onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = getFirstTwoNames(character.name),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
            )
            Spacer(modifier = Modifier.weight(1f))
            Badge(
                containerColor = when (character.status) {
                    "Alive" -> Color(0xFFA5DF8F)
                    "Dead" -> BadgeDefaults.containerColor
                    else -> Color.Gray
                },
                content = {
                    Text(
                        text = character.status,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            )
        }
    }
}

fun getFirstTwoNames(fullName: String): String {
    val names = fullName.split(" ")
    return if (names.size > 2) {
        "${names[0]} ${names[1]}"
    } else {
        fullName
    }
}
