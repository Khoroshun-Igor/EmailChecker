package com.tamago.features.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.tamago.features.R

@Composable
fun DomainItems(
    domainName: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.middle_padding))
            .clickable { onSelect(domainName) }
    ) {
        Text(domainName)
    }
}