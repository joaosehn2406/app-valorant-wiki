package com.example.valorant_app.ui.reusable_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valorant_app.R
import com.example.valorant_app.ui.navigation.AgentRoute
import com.example.valorant_app.ui.navigation.HomePageRoute
import com.example.valorant_app.ui.navigation.WeaponRoute

@Composable
fun BottomAppBarNav(
    navController: NavController,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            val items = listOf(
                Triple(
                    "Home",
                    HomePageRoute.route,
                    painterResource(R.drawable.home_icon_silhouette)
                ),
                Triple(
                    stringResource(R.string.agent),
                    AgentRoute.route,
                    painterResource(R.drawable.imagem_fe)
                ),
                Triple(
                    stringResource(R.string.weapon),
                    WeaponRoute.route,
                    painterResource(R.drawable.arma_fe)
                )
            )

            items.forEach { (label, route, icon) ->
                NavigationBarItem(
                    selected = currentRoute == route,
                    onClick = {
                        if (currentRoute != route) navController.navigate(route)
                    },
                    icon = {
                        Icon(
                            painter = icon,
                            contentDescription = label,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = {
                        Text(
                            text = label,
                            color = if (currentRoute == route)
                                MaterialTheme.colorScheme.scrim
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.scrim,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }
}
