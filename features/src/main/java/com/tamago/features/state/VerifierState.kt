package com.tamago.features.state

import com.tamago.email_data.model.Verifier

//empty data class for mutable state in viewmodel
data class VerifierState(
    val verifierResult: Verifier? = null,
    val error:String= "",
    val isLoading: Boolean = false,
    val message: String = ""
)