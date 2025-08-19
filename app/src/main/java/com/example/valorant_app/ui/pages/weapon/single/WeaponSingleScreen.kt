package com.example.valorant_app.ui.pages.weapon.single

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.valorant_app.R
import com.example.valorant_app.data.entities.single.WeaponSingle
import com.example.valorant_app.ui.utils.WeaponSingleViewModelEntryPoint
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import com.example.valorant_app.ui.reusable_comp.WeaponSingleTopBar
import dagger.hilt.android.EntryPointAccessors

@Composable
fun WeaponSingleScreen(
    weaponId: String,
    currentRoute: String,
    navController: NavController
) {
    val context = LocalContext.current
    val factory = EntryPointAccessors.fromActivity(
        context as Activity,
        WeaponSingleViewModelEntryPoint::class.java
    ).weaponSingleViewModelFactory()
    val viewModel: WeaponSingleViewModel = viewModel(
        factory = WeaponSingleViewModelFactory(factory, weaponId)
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { WeaponSingleTopBar(navController) },
        bottomBar = { BottomAppBarNav(navController = navController, currentRoute = currentRoute) }
    ) { paddingValues ->
        when (uiState) {
            is WeaponSingleUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            is WeaponSingleUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (uiState as WeaponSingleUiState.Error).message,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }

            is WeaponSingleUiState.Success -> {
                WeaponDetailsContent(
                    weapon = (uiState as WeaponSingleUiState.Success).weapon,
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun WeaponDetailsContent(
    weapon: WeaponSingle,
    paddingValues: PaddingValues
) {
    val pagerState = rememberPagerState(pageCount = { weapon.skins.size }, initialPage = 0)
    val mainScrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .padding(paddingValues)
                .verticalScroll(mainScrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp)
            ) { page ->
                val currentSkin = weapon.skins[page]
                var selectedChroma by remember(page) { mutableStateOf(currentSkin.chromas.first()) }

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
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface,
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
                                text = stringResource(R.string.skin_unavailable),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    if (currentSkin.chromas.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.variants).uppercase(),
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            items(currentSkin.chromas) { chroma ->
                                if (!chroma.swatch.isNullOrEmpty()) {
                                    val isSelected = chroma == selectedChroma
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
                                                .background(
                                                    if (isSelected) MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.15f
                                                    )
                                                    else MaterialTheme.colorScheme.surfaceContainerLow
                                                )
                                                .padding(4.dp)
                                                .clickable { selectedChroma = chroma },
                                            contentScale = ContentScale.Fit
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = if (chroma.displayName.isNullOrEmpty()) "Padrão" else chroma.displayName,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                                                .background(MaterialTheme.colorScheme.surfaceContainer)
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = if (chroma.displayName.isNullOrEmpty()) "Padrão" else chroma.displayName,
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

            Spacer(modifier = Modifier.height(28.dp))

            weapon.weaponStats?.let { stats ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.statistics).uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = stringResource(R.string.stat_fire_rate),
                        statValue = stringResource(R.string.stat_tiros_por_segundo, stats.fireRate)
                    )

                    weapon.shopData?.cost?.let { cost ->
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = stringResource(R.string.stat_price),
                            statValue = "$ $cost"
                        )
                    }

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = "Tamanho do Pente",
                        statValue = "${stats.magazineSize} balas"
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = stringResource(R.string.stat_equip_time),
                        statValue = stringResource(R.string.stat_seconds, stats.equipTimeSeconds)
                    )

                    WeaponStatItem(
                        iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                        statName = stringResource(R.string.stat_reload_time),
                        statValue = stringResource(R.string.stat_seconds, stats.reloadTimeSeconds)
                    )

                    if (stats.runSpeedMultiplier != 0f) {
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = stringResource(R.string.stat_run_speed),
                            statValue = stringResource(
                                R.string.stat_multiplier,
                                stats.runSpeedMultiplier
                            )
                        )
                    }

                    if (stats.firstBulletAccuracy != 0f) {
                        WeaponStatItem(
                            iconPainter = rememberAsyncImagePainter(model = R.drawable.arma_fe),
                            statName = stringResource(R.string.stat_first_bullet_accuracy),
                            statValue = stringResource(
                                R.string.stat_percentage,
                                (stats.firstBulletAccuracy * 100).toInt()
                            )
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
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = statName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = statValue,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}