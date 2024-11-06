package com.asociate.pj.rickandmortycompose.ui.data.repository

import com.asociate.pj.rickandmortycompose.ui.data.entity.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.RickAndMortyClient
import com.asociate.pj.rickandmortycompose.ui.data.entity.LocationRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.entity.OriginRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.response.Location
import com.asociate.pj.rickandmortycompose.ui.data.response.Origin
import com.asociate.pj.rickandmortycompose.ui.data.response.ResultRickAndMorty

class RickAndMortyRepository {


    suspend fun fetchRickAndMorty(): List<CharacterModelRickAndMorty> =
        RickAndMortyClient.instance
            .fetchCharacterRickAndMorty()
            .results
            .map { it.toDomain() }

    suspend fun fetchRickAndMortyById(id: Int): CharacterModelRickAndMorty =
        RickAndMortyClient.instance
            .fetchCharacterRickAndMortyById(id)
            .toDomain()
}

private fun ResultRickAndMorty.toDomain() = CharacterModelRickAndMorty(
    id = id,
    name = name,
    episode = episode,
    gender = gender,
    image = image,
    location = location.toLocationModel(),
    origin = origin.toOriginModel(),
    species = species,
    status = status,
    type = type
)

private fun Location.toLocationModel() =
    LocationRickAndMorty(name, url)

private fun Origin.toOriginModel() =
    OriginRickAndMorty(name, url)