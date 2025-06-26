package com.example.valorant_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.valorant_app.ui.pages.InitialScreen
import com.example.valorant_app.ui.theme.ValorantappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValorantappTheme {
                InitialScreen()
            }
        }
    }
}

