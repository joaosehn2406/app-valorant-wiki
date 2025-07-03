package com.example.valorant_app.ui.reusable_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valorant_app.R
import com.example.valorant_app.data.utils.FlagHexConverter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    currentRoute: String,
    topBar: @Composable () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        },
        containerColor = Color.Transparent
    ) { inner ->
        content(Modifier.padding(inner))
    }
}


@Composable
fun FlagsDropdown(
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.background(
            Color.Transparent
        )
    ) {
        IconButton(
            onClick = {expanded = !expanded}
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("BR")} - ${stringResource(R.string.dropdown_portuguese)}") },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("US")} - ${stringResource(R.string.dropdown_english)}") },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("ES")} - ${stringResource(R.string.dropdown_spanish)}") },
                onClick = { }
            )
        }
    }
}