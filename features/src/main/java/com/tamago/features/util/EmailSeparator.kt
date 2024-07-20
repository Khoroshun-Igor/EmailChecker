package com.tamago.features.util

/**
 * Created by Igor Khoroshun on 01.07.2024.
 */

//substring email before domain
fun separateEmail(
    email: String
): String =
    email.substring(email.lastIndexOf('@') + 1)

//adding autocomplete domain
fun domainFinder(
    email: String,
    domainName: String
): String =
    email.substring(
        0,
        email.lastIndexOf('@') + 1
    ) + domainName