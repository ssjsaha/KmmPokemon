package presentation

sealed class PokeUiEvent {
    data object GetPokemonList: PokeUiEvent()
}