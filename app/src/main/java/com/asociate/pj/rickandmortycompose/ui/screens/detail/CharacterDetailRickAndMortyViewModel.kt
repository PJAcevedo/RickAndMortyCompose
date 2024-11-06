package com.asociate.pj.rickandmortycompose.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.asociate.pj.rickandmortycompose.ui.data.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.characters

class CharacterDetailRickAndMortyViewModel(private val id: Int) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        state = UiState(loading = true)
        state = UiState(
            character = id.let { characters.find { it.id == id } },
            loading = false
        )
    }

    data class UiState(
        val character: CharacterModelRickAndMorty? = null,
        val loading: Boolean = false
    )

}