package com.tamago.features.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.tamago.features.R
import com.tamago.features.state.VerifierState

@Composable
fun VerifiedStateContent(
    state: VerifierState
) {
    if (!state.isLoading && state.verifierResult != null) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.status) + " " + when (state.verifierResult.status) {
                        stringResource(R.string.deliverable),
                        stringResource(R.string.risky) -> "The ${state.verifierResult.email} is valid"

                        else -> stringResource(R.string.error)
                    }
                )
                if (state.verifierResult.status == stringResource(R.string.undeliverable)
                    || state.verifierResult.status == stringResource(R.string.unknown)
                )
                    Text(
                        text = stringResource(R.string.reason) + " " + when (state.verifierResult.reason) {
                            stringResource(R.string.invalid_email) -> state.verifierResult.email + " " + stringResource(
                                R.string.invalid_email_reason
                            )

                            stringResource(R.string.invalid_domain) ->
                                state.verifierResult.domain?.name + " " + stringResource(
                                    R.string.invalid_domain_reason
                                )

                            stringResource(R.string.rejected_email) -> state.verifierResult.email
                            else -> ""
                        }
                    )
                if (state.verifierResult.didYouMean != null)
                    Text(text = stringResource(R.string.did_you_mean) + " " + state.verifierResult.didYouMean)
            }
        }
    }
    if (state.error.isNotBlank()) {
        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
    }
}