package com.example.valorant_app.ui.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.valorant_app.ui.reusable_comp.SectionCard
import com.example.valorant_app.ui.reusable_comp.FeatureRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black, Color(0xFF1A001F), Color(0xFFFF4655)),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFF4655), Color(0xFF580000))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bem-vindo ao Valorant Wiki",
                    fontSize = 24.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            SectionCard(
                title = "Sobre este App",
                content = "Reúne o melhor da Valorant API de forma rápida e elegante.",
                accent = Color(0xFFFF4655)
            )

            SectionCard(
                title = "Funcionalidades",
                accent = Color(0xFFFF4655)
            ) {
                FeatureRow(Icons.Default.Person, "Explorar Agentes")
                FeatureRow(Icons.Default.Build, "Navegar por Armas")
                FeatureRow(Icons.Default.Search, "Buscar skins")
            }

            SectionCard(
                title = "Fontes de Dados",
                accent = Color(0xFFFF4655)
            ) {
                Text(
                    "• Valorant API oficial (valorant-api.com)",
                    color = Color.Cyan,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
