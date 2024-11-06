package com.asociate.pj.rickandmortycompose.ui.data.entity

data class CharacterModelRickAndMorty(
    val id: Int,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: LocationRickAndMorty,
    val name: String,
    val origin: OriginRickAndMorty,
    val species: String,
    val status: String,
    val type: String
)