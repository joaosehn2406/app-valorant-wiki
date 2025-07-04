package com.example.valorant_app.ui.reusable_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valorant_app.R
import com.example.valorant_app.ui.navigation.HomePageRoute
import com.example.valorant_app.ui.navigation.AgentRoute
import com.example.valorant_app.ui.navigation.WeaponRoute
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun BottomAppBarNav(
    navController: NavController,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    val black = Color(0xFF000000)
    val dGray = Color(0xFF111111)
    val midGray = Color(0xFF1F1B24)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    0.0f to black,
                    0.25f to dGray,
                    0.5f to midGray,
                    0.75f to dGray,
                    1.0f to black
                )
            )
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
                        if (currentRoute != route) navController.navigate(route) {
                        }
                    },

                    icon = {
                        Icon(
                            painter = icon,
                            contentDescription = label,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = { Text(label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        indicatorColor = ValorantRed,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray
                    )
                )
            }
        }
    }
}
