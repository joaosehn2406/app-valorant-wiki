package com.example.valorant_app.ui.pages.weapon.single

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.valorant_app.R
import com.example.valorant_app.data.entities.single.WeaponSingle
import com.example.valorant_app.ui.theme.ValorantRed

class SimpleColorPainter(private val color: Color) : Painter() {
    override val intrinsicSize: Size = Size.Unspecified
    override fun DrawScope.onDraw() {
        drawRect(color = color)
    }
}

@Composable
fun WeaponSingleScreen(
    weaponId: String,
    navController: NavController,
    weaponSingleViewModel: WeaponSingleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    weaponSingleViewModel.fetchWeaponSingle(weaponId)

    val uiState = weaponSingleViewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        is WeaponSingleUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0E0E10)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }
        is WeaponSingleUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0E0E10)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.message,
                    color = Color.Red,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
        is WeaponSingleUiState.Success -> {
            WeaponDetailsContent(weapon = uiState.weapon)
        }
    }
}

@Composable
fun WeaponDetailsContent(
    weapon: WeaponSingle,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { weapon.skins.size }, initialPage = 0)
    val mainScrollState = rememberScrollState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = R.drawable.imagem_fundo_boa,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC000000)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(mainScrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = weapon.displayName.uppercase(),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                ),
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp, bottom = 4.dp)
            )

            Text(
                text = "Categoria: ${weapon.category}",
                style = MaterialTheme.typography.titleMedium,
                color = ValorantRed,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0x33FFFFFF))
                    .padding(8.dp)
            ) { page ->
                var currentSkin by remember { mutableStateOf(weapon.skins[page]) }
                var selectedChroma by remember(currentSkin) { mutableStateOf(currentSkin.chromas.first()) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (!currentSkin.displayIcon.isNullOrEmpty()) {
                        AsyncImage(
                            model = selectedChroma.fullRender,
                            contentDescription = "Skin: ${currentSkin.displayName}",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(bottom = 12.dp)
                        )

                        Text(
                            text = currentSkin.displayName,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Skin indisponível",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    if (currentSkin.chromas.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "VARIANTES",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = ValorantRed,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            items(currentSkin.chromas) { chroma ->
                                if (!chroma.swatch.isNullOrEmpty()) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    ) {
                                        AsyncImage(
                                            model = chroma.swatch,
                                            contentDescription = "Chroma: ${chroma.displayName}",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color.DarkGray.copy(alpha = 0.6f))
                                                .padding(4.dp)
                                                .clickable {

                                                },
                                            contentScale = ContentScale.Fit
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = if (chroma.displayName.isNullOrEmpty()) "Padrão" else chroma.displayName,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.LightGray
                                        )
                                    }
                                } else {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color.Gray.copy(alpha = 0.6f))
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = if (chroma.displayName.isNullOrEmpty()) "Padrão" else chroma.displayName,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.LightGray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            weapon.weaponStats?.let { stats ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "ESTATÍSTICAS",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        ),
                        color = ValorantRed,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = "Taxa de Tiro",
                        statValue = "${stats.fireRate} tiros/s"
                    )

                    weapon.shopData?.cost?.let { cost ->
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = "Preço",
                            statValue = "$ ${cost}"
                        )
                    }

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = "Tamanho do Pente",
                        statValue = "${stats.magazineSize} balas"
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = "Tempo de Equipamento",
                        statValue = "${stats.equipTimeSeconds}s"
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = "Tempo de Recarga",
                        statValue = "${stats.reloadTimeSeconds}s"
                    )

                    if (stats.runSpeedMultiplier != 0f) {
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = "Velocidade de Corrida",
                            statValue = "${stats.runSpeedMultiplier}x"
                        )
                    }

                    if (stats.firstBulletAccuracy != 0f) {
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = "Precisão da Primeira Bala",
                            statValue = "${(stats.firstBulletAccuracy * 100).toInt()}%"
                        )
                    }

                    if (!stats.wallPenetration.isNullOrEmpty()) {
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = "Penetração na Parede",
                            statValue = stats.wallPenetration
                        )
                    }

                    stats.adsStats?.let { ads ->
                        Text(
                            text = "ESTATÍSTICAS ADS",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            ),
                            color = ValorantRed.copy(alpha = 0.8f),
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                        )
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = "Multiplicador de Zoom",
                            statValue = "${ads.zoomMultiplier}x"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeaponStatItem(
    iconPainter: Painter,
    statName: String,
    statValue: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = iconPainter,
            contentDescription = "$statName icon",
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.DarkGray.copy(alpha = 0.4f))
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = statName,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = statValue,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }
    }
}