package com.asociate.pj.rickandmortycompose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.asociate.pj.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    RickAndMortyComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}