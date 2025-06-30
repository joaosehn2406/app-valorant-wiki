package com.example.valorant_app.ui.pages

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier       = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Valorant Wiki",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = Color(0xFF0E0E10),
                    titleContentColor = Color(0xFFFF4655)
                )
            )
        },
        bottomBar = {
            BottomAppBarNav(navController)
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black, Color(0xFF1A001F), Color(0xFFFF4655)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .padding(inner)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Hero Banner
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

                // Sessões estáticas
                SectionCard(
                    title   = "Sobre este App",
                    content = "Reúne o melhor da Valorant API de forma rápida e elegante.",
                    accent  = Color(0xFFFF4655)
                )

                SectionCard(
                    title  = "Funcionalidades",
                    accent = Color(0xFFFF4655)
                ) {
                    FeatureRow(Icons.Default.Person,    "Explorar Agentes")
                    FeatureRow(Icons.Default.Build,     "Navegar por Armas")
                    FeatureRow(Icons.Default.Search,    "Busca integrada")
                }

                SectionCard(
                    title  = "Fontes de Dados",
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
}

@Composable
private fun SectionCard(
    title: String,
    content: String? = null,
    accent: Color,
    contentBlock: (@Composable ColumnScope.() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF191A1C)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier
                        .width(4.dp)
                        .height(24.dp),
                    color = accent
                )
                Spacer(Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            content?.let {
                Text(it, style = MaterialTheme.typography.bodyMedium, color = Color.LightGray)
            }
            contentBlock?.invoke(this)
        }
    }
}

@Composable
private fun FeatureRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(icon, contentDescription = text, modifier = Modifier.size(20.dp), tint = Color(0xFFFF4655))
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall, color = Color.White)
    }
}
