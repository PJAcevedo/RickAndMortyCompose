package com.asociate.pj.rickandmortycompose.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.asociate.pj.rickandmortycompose.ui.data.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.characters

class CharactersRickAndMortyViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        state = UiState(loading = true)
        state = UiState(
            characters = characters,
            loading = false
        )
    }

    data class UiState(
        val characters: List<CharacterModelRickAndMorty> = emptyList(),
        val loading: Boolean = false
    )

}