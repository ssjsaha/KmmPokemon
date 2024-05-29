import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.PokemonRepositoryImpl
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import org.koin.compose.koinInject

import watersplash.composeapp.generated.resources.Res
import watersplash.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "PokemonList"
            ) {
                composable("PokemonList") {
                    val viewModel = viewModel<MainViewModel> {
                        MainViewModel(PokemonRepositoryImpl())
                    }
                    PokeListComposable(viewModel.uiState, viewModel::onEvent)
                }
            }
        }

        /* var showContent by remember { mutableStateOf(false) }
         Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
             Button(onClick = { showContent = !showContent }) {
                 Text("Click me!")
             }
             AnimatedVisibility(showContent) {
                 val greeting = remember { Greeting().greet() }
                 Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                     Image(painterResource(Res.drawable.compose_multiplatform), null)
                     Text("Compose: $greeting")
                 }
             }
         }*/
    }
}

/*
@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}*/
