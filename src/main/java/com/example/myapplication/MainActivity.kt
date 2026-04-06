package com.example.myapplication

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.android.gms.ads.MobileAds
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback


@Composable
fun rememberRewardedAd(onEarned: () -> Unit): () -> Unit {
    val context = LocalContext.current
    var rewardedAd by remember { mutableStateOf<RewardedAd?>(null) }

    LaunchedEffect(Unit) {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            context,
            "ca-app-pub-7782833017648262/3120056731", // Тестовий Rewarded ID
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                }
            }
        )
    }

    return {
        rewardedAd?.show(context as ComponentActivity) { rewardItem ->
            onEarned()
        }
    }
}
@Composable
fun BannerAd(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp), // стандартна висота банера
        factory = { ctx ->
            AdView(ctx).apply {
                // Фіксований банер 320x50
                setAdSize(AdSize.BANNER)
                // ТЕСТОВИЙ банер Ad Unit ID від Google
                // Використовувати тільки під час розробки!
                adUnitId = "ca-app-pub-7782833017648262/5050540916"

                val adRequest = AdRequest.Builder().build()
                loadAd(adRequest)
            }
        }
    )
}class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MobileAds.initialize(this) {}
        setContent {
            WallpaperApp()
        }
    }
}

// --------------------- Navigation Destinations ------------------------

// Simple sealed class for navigation routes
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Detail : Screen("detail/{wallpaperId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}

// --------------------- Root Composable ------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperApp() {
    val navController = rememberNavController()
    val wallpapers = remember { WallpaperData.wallpapers }

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            // Splash Screen
            composable(Screen.Splash.route) {
                SplashScreen(
                    onTimeout = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            // Home Screen (grid of wallpapers)
            composable(Screen.Home.route) {
                HomeScreen(
                    wallpapers = wallpapers,
                    onWallpaperClick = { wallpaper ->
                        navController.navigate(Screen.Detail.createRoute(wallpaper.id))
                    }
                )
            }

            // Detail Screen (full-screen wallpaper + Set button)
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("wallpaperId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("wallpaperId")
                val wallpaper = wallpapers.firstOrNull { it.id == id }
                if (wallpaper != null) {
                    DetailScreen(wallpaper = wallpaper)
                }
            }
        }
    }
}

// --------------------- Splash Screen ------------------------

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        // Splash delay (1.5 seconds)
        kotlinx.coroutines.delay(1500)
        onTimeout()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Simple logo replacement (text-based)
                Text(
                    text = "★",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // APP NAME FROM STRINGS.XML
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// --------------------- Home Screen (Grid) ------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    wallpapers: List<Wallpaper>,
    onWallpaperClick: (Wallpaper) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()   // ← Додаємо відступ під кнопки системи
            ) {
                BannerAd()
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 140.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(wallpapers) { wallpaper ->
                WallpaperCard(
                    wallpaper = wallpaper,
                    onClick = { onWallpaperClick(wallpaper) }
                )
            }
        }
    }
}

@Composable
fun WallpaperCard(
    wallpaper: Wallpaper,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = wallpaper.imageUrl,
                contentDescription = wallpaper.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = wallpaper.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// --------------------- Detail Screen ------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(wallpaper: Wallpaper) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = wallpaper.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                val showRewarded = rememberRewardedAd {
                    // Код, який виконається після перегляду реклами
                    scope.launch {
                        val success = setWallpaperFromUrl(context, wallpaper.imageUrl)
                        Toast.makeText(
                            context,
                            if (success) context.getString(R.string.set_wallpaper_success)
                            else context.getString(R.string.set_wallpaper_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                Button(
                    onClick = {
                        showRewarded()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.set_as_wallpaper))
                }

            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = wallpaper.imageUrl,
                contentDescription = wallpaper.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

// --------------------- Helper: load bitmap + set wallpaper ------------------------

private suspend fun setWallpaperFromUrl(
    context: android.content.Context,
    imageUrl: String
): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false) // Need software bitmap for WallpaperManager
                .build()

            val result = loader.execute(request)
            if (result is SuccessResult) {
                val drawable = result.drawable
                val bitmap = (drawable as BitmapDrawable).bitmap

                val wallpaperManager = WallpaperManager.getInstance(context)
                wallpaperManager.setBitmap(bitmap)

                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
