package domain.repo

import Resource
import domain.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepo {
    suspend fun getPokemonList(): Flow<Resource<PokemonResponse>>
}