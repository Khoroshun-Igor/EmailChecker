package com.tamago.features

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamago.email_data.toVerifier
import com.tamago.features.response.VerifierResponse
import com.tamago.features.state.VerifierState
import com.tamago.features.usecases.GetVerificationDataUseCase
//import com.tamago.features.response.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

@HiltViewModel
class EmailCheckerViewModel @Inject constructor(
    private val getVerificationDataUseCase: GetVerificationDataUseCase,
) : ViewModel() {

    var email by mutableStateOf("")
    private val _state = mutableStateOf(VerifierState())
    val state: State<VerifierState> = _state

    private val scope = CoroutineScope(Dispatchers.IO)
    private var unusuallyLongResponse: Boolean = false

    fun getVerifierInfo(email: String) {
        viewModelScope.launch {
            getVerificationDataUseCase.getVerifierInfo(email).collect {
                when (it) {
                    is VerifierResponse.Error -> {
                        _state.value = VerifierState(
                            error = it.message ?: ""
                        )
                        unusuallyLongResponse = false
                    }
                    is VerifierResponse.Loading -> {
                        _state.value = VerifierState(
                            isLoading = true
                        )
                        unusuallyLongResponse = true
                        scope.launch {
                            if(unusuallyLongResponse){
                                _state.value = VerifierState(
                                    isLoading = true,
                                    message = "is loading"
                                )
                            }
                        }
                    }//for a long request
                    is VerifierResponse.Success -> {
                        _state.value = VerifierState(
                            verifierResult = it.data?.toVerifier()
                        )
                        unusuallyLongResponse = false
                    }
                }
            }
        }
    }
}

