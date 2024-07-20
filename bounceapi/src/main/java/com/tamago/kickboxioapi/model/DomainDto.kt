package com.tamago.kickboxioapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Igor Khoroshun on 30.06.2024.
 */

@Serializable
data class DomainDto(
    @SerialName("name")
    val name: String,
    @SerialName("acceptAll")
    val acceptAll: String,
    @SerialName("disposable")
    val disposable: String,
    @SerialName("free")
    val free: String,
)
