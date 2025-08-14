package com.example.valorant_app.ui.pages.agent.single

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.valorant_app.data.utils.toComposeColor
import com.example.valorant_app.ui.WeaponSingleViewModelEntryPoint
import com.example.valorant_app.ui.reusable_comp.AgentSingleTopBar
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import dagger.hilt.android.EntryPointAccessors

class SimpleColorPainter(private val color: Color) : Painter() {
    override val intrinsicSize: androidx.compose.ui.geometry.Size =
        androidx.compose.ui.geometry.Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRect(color = color)
    }
}

@Composable
fun AgentSingleScreen(
    agentId: String,
    currentRoute: String,
    navController: NavController
) {
    val context = LocalContext.current

    val factory = EntryPointAccessors.fromActivity(
        context as Activity,
        WeaponSingleViewModelEntryPoint::class.java
    ).agentSingleViewModelFactory()

    val viewModel: AgentSingleViewModel = viewModel(
        factory = AgentSingleViewModelFactory(factory, agentId)
    )

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AgentSingleTopBar(navController)
        },
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        when (state) {
            is AgentSingleUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primaryContainer)
                }
            }

            is AgentSingleUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (state as AgentSingleUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }

            is AgentSingleUiState.Success -> {
                val agent = (state as AgentSingleUiState.Success).agent
                val scrollState = rememberScrollState()

                val palette = agent.backgroundGradientColors
                    .orEmpty()
                    .map { it.toComposeColor() }

                val accent = palette.firstOrNull() ?: MaterialTheme.colorScheme.primary
                val accent2 = palette.getOrNull(1) ?: palette.lastOrNull() ?: accent
                val headerGradient = Brush.verticalGradient(listOf(accent, accent2))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .background(headerGradient)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = agent.background,
                                    placeholder = SimpleColorPainter(Color.Transparent),
                                    error = SimpleColorPainter(Color.Black)
                                ),
                                contentDescription = "Foto do background",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = agent.fullPortrait,
                                    placeholder = SimpleColorPainter(Color.Transparent),
                                    error = SimpleColorPainter(Color.Black)
                                ),
                                contentDescription = "Foto do agente",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = agent.displayName,
                                style = MaterialTheme.typography.headlineMedium,
                                color = accent,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = agent.description,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .background(accent.copy(alpha = 0.05f))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "Role: ${agent.role?.displayName ?: "Unknown"}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = accent
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            agent.abilities.forEach { ability ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            model = ability.displayIcon,
                                            placeholder = SimpleColorPainter(MaterialTheme.colorScheme.surfaceVariant)
                                        ),
                                        contentDescription = ability.displayName,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(accent)
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            text = ability.displayName,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = ability.description,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}