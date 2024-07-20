package com.tamago.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tamago.features.components.EmailField
import com.tamago.features.components.VerifiedStateContent

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

@Composable
fun EmailCheckerMainScreen() {
    EmailMainScreenContent(viewModel = viewModel())
}

@Composable
fun EmailMainScreenContent(
    viewModel: EmailCheckerViewModel,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        content = {
            EmailChecker(
                viewModel = viewModel,
                modifier = modifier.padding(it)
            )
        }
    )
}

@Composable
fun EmailChecker(
    viewModel: EmailCheckerViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.value
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = dimensionResource(R.dimen.large_padding))
        ) {
            EmailField(
                viewModel,
                state,
            )
            VerifiedStateContent(state = state)
        }
    }
}
