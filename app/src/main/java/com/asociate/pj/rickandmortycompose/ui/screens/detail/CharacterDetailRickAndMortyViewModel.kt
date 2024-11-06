package com.asociate.pj.rickandmortycompose.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asociate.pj.rickandmortycompose.ui.data.entity.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.repository.RickAndMortyRepository
import kotlinx.coroutines.launch

class CharacterDetailRickAndMortyViewModel(private val id: Int) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = RickAndMortyRepository()

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                character =  repository.fetchRickAndMortyById(id),
                loading = false
            )
        }
    }

    data class UiState(
        val character: CharacterModelRickAndMorty? = null,
        val loading: Boolean = false
    )

}