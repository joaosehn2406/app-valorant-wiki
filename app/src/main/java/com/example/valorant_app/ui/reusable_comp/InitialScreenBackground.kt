package com.example.valorant_app.ui.reusable_comp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ValorantBackground(
    @DrawableRes backgroundImg: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(backgroundImg),
        contentDescription = "Initial screen background",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .alpha(0.8f)
    )
}