package com.tamago.kickboxioapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Igor Khoroshun on 30.06.2024.
 */

@Serializable
data class DNSDto(
    @SerialName("type")
    val type: String,
    @SerialName("record")
    val record: String? = null,
)
