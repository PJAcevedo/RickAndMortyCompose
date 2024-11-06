package com.asociate.pj.rickandmortycompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asociate.pj.rickandmortycompose.ui.data.CharacterModelRickAndMorty
import com.asociate.pj.rickandmortycompose.ui.screens.detail.CharacterDetail
import com.asociate.pj.rickandmortycompose.ui.screens.detail.CharacterDetailRickAndMortyViewModel
import com.asociate.pj.rickandmortycompose.ui.screens.home.CharactersRickAndMorty

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            CharactersRickAndMorty(
                onCharacterDetailClick = { character ->
                    navController.navigate("detail/${character.id}")
                }
            )
        }

        composable(
            route = "detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            val characterId = requireNotNull(it.arguments?.getInt("characterId"))
            CharacterDetail(
                viewModel = CharacterDetailRickAndMortyViewModel(characterId),
                onBack = { navController.popBackStack() }
            )
        }
    }

}