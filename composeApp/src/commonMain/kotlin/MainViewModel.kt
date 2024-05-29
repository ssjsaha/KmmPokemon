import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.repo.PokemonRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.PokeListUiState
import presentation.PokeUiEvent

class MainViewModel(private val repo: PokemonRepo) : ViewModel() {

    private val _uiState: MutableStateFlow<PokeListUiState> = MutableStateFlow(PokeListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(PokeUiEvent.GetPokemonList)
    }

    fun onEvent(event: PokeUiEvent){
        when(event){
            is PokeUiEvent.GetPokemonList->{
                getPokemonList()
            }
        }
    }

    private fun getPokemonList(){
        viewModelScope.launch {

            repo.getPokemonList().collect{
                when(it){
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            data = it.data?.results?: emptyList(),
                            loading =  false,
                            error = ""
                        )
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            loading =  true,
                        )
                    }

                    is Resource.Error ->{
                        _uiState.value = _uiState.value.copy(
                            loading = false,
                            error = it.error?:"Something went wrong"
                        )
                    }
                }
            }
        }
    }

}