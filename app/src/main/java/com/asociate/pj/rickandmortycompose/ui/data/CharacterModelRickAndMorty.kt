package com.asociate.pj.rickandmortycompose.ui.data

data class CharacterModelRickAndMorty(
    val id: Int,
    val name: String,
    val image: String,
    val age: Int = 25
)

val characters = (1..50).map {
    CharacterModelRickAndMorty(
        id = it,
        name = "Character $it",
        image = "https://rickandmortyapi.com/api/character/avatar/$it.jpeg"
    )
}