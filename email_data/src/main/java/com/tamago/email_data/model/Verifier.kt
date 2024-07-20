package com.tamago.email_data.model

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

/**
 * For now, we only need this information from API
 */
data class Verifier(
    val email: String,
    val status: String,
    val reason: String,
    val domain: Domain?,
    val didYouMean: String?
)
