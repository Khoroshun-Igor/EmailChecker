package com.tamago.kickboxioapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Igor Khoroshun on 30.06.2024.
 */

@Serializable
data class AccountDto(
    @SerialName("role")
    val role: String,
    @SerialName("disabled")
    val disabled: String,
    @SerialName("fullMailbox")
    val fullMailbox: String
)
