package com.tamago.features.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tamago.emailchecker.util.Constants
import com.tamago.features.EmailCheckerViewModel
import com.tamago.features.R
import com.tamago.features.state.VerifierState
import com.tamago.features.util.domainFinder
import com.tamago.features.util.separateEmail

/**
 * Created by Igor Khoroshun on 30.06.2024.
 */

@Composable
fun EmailField(
    viewModel: EmailCheckerViewModel,
    state: VerifierState,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { newValue ->
                viewModel.email = newValue
                expanded = true
            },
            singleLine = true,
            placeholder = { Text(text = stringResource(R.string.Email)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(R.string.Email)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.getVerifierInfo(viewModel.email)
                }
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = stringResource(R.string.Check)
                        )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            modifier = modifier.fillMaxWidth()
        )
        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = modifier.fillMaxWidth()
            ) {
                LazyColumn(modifier = Modifier.heightIn(max = 150.dp)) {
                    if (viewModel.email.contains(Regex(Constants.PATTERN))) {
                        val domainSubstring = separateEmail(viewModel.email)
                        items(
                            Constants.domainsName.filter {
                                it.lowercase().startsWith(domainSubstring.lowercase())
                            }
                        ) {
                            DomainItems(
                                domainName = it,
                                onSelect = { domainName ->
                                    viewModel.email = domainFinder(viewModel.email, domainName)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}