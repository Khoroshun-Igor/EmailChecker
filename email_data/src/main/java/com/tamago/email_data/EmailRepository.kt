package com.tamago.email_data

import com.tamago.kickboxioapi.BouncerApi
import com.tamago.kickboxioapi.model.VerifierDto
import javax.inject.Inject

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

class EmailRepository @Inject constructor(
    private val api: BouncerApi
) {
    suspend fun getVerificationData(
        email: String
    ): VerifierDto{
        return api.verifyEmail(email)
    }
}