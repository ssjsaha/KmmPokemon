package domain.model
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Long,
    val prev: String?,
    val next: String?,
    val results: List<Pokemon> = listOf()
)