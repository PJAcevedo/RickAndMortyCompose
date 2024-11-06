package com.asociate.pj.rickandmortycompose.ui.data

import com.asociate.pj.rickandmortycompose.ui.data.response.BaseRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.data.response.ResultRickAndMorty
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character")
    suspend fun fetchCharacterRickAndMorty() : BaseRickAndMorty

    @GET("character/{id}")
    suspend fun fetchCharacterRickAndMortyById(@Path("id") characterId: Int) : ResultRickAndMorty

}