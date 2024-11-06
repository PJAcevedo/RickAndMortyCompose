package com.asociate.pj.rickandmortycompose.ui.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseRickAndMorty(
    @SerialName("results")
    val results: List<ResultRickAndMorty>
)