package com.example.valorant_app.ui.reusable_comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.R
import com.example.valorant_app.ui.destinations.AgentRoute
import com.example.valorant_app.ui.destinations.HomePageRoute
import com.example.valorant_app.ui.destinations.Route
import com.example.valorant_app.ui.destinations.WeaponRoute

@Composable
fun BottomAppBarNav(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButtonReusable(
                navController = navController,
                destination = HomePageRoute,
                iconPainter = painterResource(id = R.drawable.home_icon_silhouette),
                contentDescription = "Home icon",
                modifier = Modifier.size(30.dp),
                buttonText = "Home"
            )
            IconButtonReusable(
                navController = navController,
                destination = AgentRoute,
                iconPainter = painterResource(id = R.drawable.imagem_fe),
                contentDescription = "Agent icon",
                modifier = Modifier.size(30.dp),
                buttonText = "Agente"
            )
            IconButtonReusable(
                navController = navController,
                destination = WeaponRoute,
                iconPainter = painterResource(id = R.drawable.arma_fe),
                contentDescription = "Weapon icon",
                modifier = Modifier.size(34.dp),
                buttonText = "Armas"
            )
        }
    }
}

@Composable
fun IconButtonReusable(
    navController: NavController,
    destination: Route,
    iconPainter: Painter,
    contentDescription: String,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        IconButton(
            onClick = { navController.navigate(destination.route) },
            modifier = modifier
        ) {
            Image(
                painter = iconPainter,
                contentDescription = contentDescription
            )
        }
        Text(
            text = buttonText,
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current
        )
    }
}

@Preview
@Composable
private fun BottomAppBarNavPreview() {
    val navController = rememberNavController()

    BottomAppBarNav(
        navController = navController
    )
}
