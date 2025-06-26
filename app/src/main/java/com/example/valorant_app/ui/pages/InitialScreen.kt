package com.example.valorant_app.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.valorant_app.R
import com.example.valorant_app.ui.background.ValorantBackground
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun InitialScreen(
    navigateToHomePage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ValorantBackground(
            backgroundImg = R.drawable.ic_initial_screen_background,
            modifier = Modifier
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier.padding(top = 180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ValorantRed,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = { navigateToHomePage() }
            ) {
                Text(
                    text = "Vamos la?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0f, 0f)
                        )
                    )
                )
            }
        }

    }
}

@Preview
@Composable
private fun InitialScreenPreview() {
    InitialScreen(
        navigateToHomePage = {}
    )
}