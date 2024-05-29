sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(msg: String) : Resource<T>(error = msg)
    class Loading<T>(loading: Boolean) : Resource<T>(loading = loading)
}