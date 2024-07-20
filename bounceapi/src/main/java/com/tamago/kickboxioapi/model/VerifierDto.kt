package com.tamago.kickboxioapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */
/**
 * Data Class description: https://docs.usebouncer.com/terminology
 */
@Serializable
data class VerifierDto(
    @SerialName("email")
    val email: String,
    @SerialName("status")
    val status: String,
    @SerialName("reason")
    val reason: String,
    @SerialName("domain")
    val domain : DomainDto? = null,
    @SerialName("account")
    val account : AccountDto? = null,
    @SerialName("dns")
    val dns: DNSDto? = null,
    @SerialName("provider")
    val provider: String = "",
    @SerialName("didYouMean")
    val didYouMean: String? = null,
    @SerialName("score")
    val score: Int,
    @SerialName("toxic")
    val toxic: String,
)
