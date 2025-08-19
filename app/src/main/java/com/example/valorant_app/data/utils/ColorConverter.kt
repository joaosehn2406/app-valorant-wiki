package com.example.valorant_app.data.utils

import androidx.compose.ui.graphics.Color

fun String.toComposeColor(): Color {
    val r = substring(0, 2).toInt(16)
    val g = substring(2, 4).toInt(16)
    val b = substring(4, 6).toInt(16)
    val a = substring(6, 8).toInt(16)
    return Color(red = r, green = g, blue = b, alpha = a)
}