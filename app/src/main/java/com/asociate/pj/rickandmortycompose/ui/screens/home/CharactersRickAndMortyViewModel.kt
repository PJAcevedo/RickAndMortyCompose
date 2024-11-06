package com.asociate.pj.rickandmortycompose.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asociate.pj.rickandmortycompose.ui.data.entity.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.repository.RickAndMortyRepository
import kotlinx.coroutines.launch

class CharactersRickAndMortyViewModel : ViewModel() {

    private val repository = RickAndMortyRepository()

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                characters = repository.fetchRickAndMorty(),
                loading = false
            )
        }
    }

    data class UiState(
        val characters: List<CharacterModelRickAndMorty> = emptyList(),
        val loading: Boolean = false
    )

}