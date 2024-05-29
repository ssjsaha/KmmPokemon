package data

import Resource
import domain.model.PokemonResponse
import domain.repo.PokemonRepo
import expects.client
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl: PokemonRepo {
    override suspend fun getPokemonList(): Flow<Resource<PokemonResponse>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = client.get{
                    url("pokemon")
                }
                emit(Resource.Success(response.body()))
            }catch (e : RedirectResponseException){
                // handle 3xx codes
                emit(Resource.Error(e.response.status.description))

            }catch (e: ClientRequestException){
                //handle 4xx error codes
                emit(Resource.Error(e.response.status.description))

            }catch (e : ServerResponseException){
                //handle 5xx error codes
                emit(Resource.Error(e.response.status.description))
            }catch (e: Exception){
                emit(Resource.Error(e.message?:"Something went wrong"))
            }
        }
    }
}