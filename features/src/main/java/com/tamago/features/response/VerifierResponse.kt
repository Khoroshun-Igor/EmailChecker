package com.tamago.features.response

//processing the response from the server
sealed class VerifierResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : VerifierResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : VerifierResponse<T>(data, message)
    class Loading<T>(data: T? = null) : VerifierResponse<T>(data)
}