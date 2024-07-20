package com.tamago.kickboxioapi

import com.tamago.kickboxioapi.model.VerifierDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

/**
 * API Documentation https://docs.usebouncer.com/introduction
 */

interface BouncerApi {
    /**
     * API Details (https://api.usebouncer.com/v1.1/email/verify)
     */
    @GET("verify")
    suspend fun verifyEmail(
        @Query("email") email: String,
    ): VerifierDto
}