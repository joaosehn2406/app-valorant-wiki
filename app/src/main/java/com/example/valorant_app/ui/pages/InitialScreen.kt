package com.example.valorant_app.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import com.example.valorant_app.R
import com.example.valorant_app.ui.reusable_comp.ValorantBackground
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun InitialScreen(
    navigateToHomePage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        ValorantBackground(
            backgroundImg = R.drawable.ic_initial_screen_background,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = navigateToHomePage,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ValorantRed,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.padding(top = 148.dp)
            ) {
                Text(
                    "Vamos lá?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        shadow = Shadow(Color.Black, Offset.Zero)
                    )
                )
            }
        }
    }
}
