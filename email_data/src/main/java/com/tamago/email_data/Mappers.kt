package com.tamago.email_data

import com.tamago.email_data.model.Domain
import com.tamago.email_data.model.Verifier
import com.tamago.kickboxioapi.model.DomainDto
import com.tamago.kickboxioapi.model.VerifierDto

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

fun VerifierDto.toVerifier(): Verifier =
    Verifier(
        email = email,
        status = status,
        reason = reason,
        didYouMean = didYouMean,
        domain = domain?.toDomain()
    )


fun DomainDto.toDomain(): Domain =
    Domain(
        name = name,
        acceptAll = when (acceptAll) {
            "yes" -> "valid email"
            "no", "unknown" -> "The domain $name does not exist or does not accept email."
            else -> "Unknown error"
        }//Additional Information
    )