package com.example.a3_education_app

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a3_education_app.ui.explore.ExoplanetDetailScreen
import com.example.a3_education_app.ui.explore.ExploreScreen
import com.example.a3_education_app.ui.favorites.FavoritesScreen
import com.example.a3_education_app.ui.home.HomeScreen
import com.example.a3_education_app.ui.lessons.LessonDetailScreen
import com.example.a3_education_app.ui.lessons.LessonsScreen
import com.example.a3_education_app.ui.quiz.ExoplanetQuizScreen
import com.example.a3_education_app.ui.quiz.PlanetQuizListScreen
import com.example.a3_education_app.ui.quiz.QuizScreen
import com.example.a3_education_app.ui.solar.SolarBodyDetailScreen
import com.example.a3_education_app.ui.solar.SolarSystemScreen

enum class SpaceScreen(@param:StringRes val title: Int) {
    Home(R.string.home),
    SolarSystem(R.string.solar_system),
    SolarDetail(R.string.solar_system),
    Exoplanets(R.string.exoplanets),
    ExoplanetDetail(R.string.exoplanets),
    Lessons(R.string.lessons),
    LessonDetail(R.string.lessons),
    Quiz(R.string.quiz),
    ExoplanetQuiz(R.string.exoplanet_blitz),
    Favorites(R.string.favorites)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceEducationApp(
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route ?: SpaceScreen.Home.name

    val currentScreen = when {
        route.startsWith(SpaceScreen.SolarDetail.name) -> SpaceScreen.SolarDetail
        route.startsWith(SpaceScreen.ExoplanetDetail.name) -> SpaceScreen.ExoplanetDetail
        route.startsWith(SpaceScreen.LessonDetail.name) -> SpaceScreen.LessonDetail
        route.startsWith(SpaceScreen.ExoplanetQuiz.name) -> SpaceScreen.ExoplanetQuiz
        route.startsWith(SpaceScreen.Quiz.name) -> SpaceScreen.Quiz
        else -> SpaceScreen.valueOf(route.substringBefore("/"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(currentScreen.title)) },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onToggleDarkTheme) {
                        Icon(
                            imageVector = if (darkTheme) {
                                Icons.Filled.LightMode
                            } else {
                                Icons.Filled.DarkMode
                            },
                            contentDescription = stringResource(R.string.toggle_theme)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SpaceScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(SpaceScreen.Home.name) {
                HomeScreen(
                    onSolarSystemClicked = { navController.navigate(SpaceScreen.SolarSystem.name) },
                    onExoplanetsClicked = { navController.navigate(SpaceScreen.Exoplanets.name) },
                    onFavoritesClicked = { navController.navigate(SpaceScreen.Favorites.name) },
                    onLessonsClicked = { navController.navigate(SpaceScreen.Lessons.name) },
                    onQuizClicked = { navController.navigate(SpaceScreen.Quiz.name) }
                )
            }
            composable(SpaceScreen.SolarSystem.name) {
                SolarSystemScreen(
                    onBodyClicked = { id ->
                        navController.navigate("${SpaceScreen.SolarDetail.name}/$id")
                    }
                )
            }
            composable(
                route = "${SpaceScreen.SolarDetail.name}/{bodyId}",
                arguments = listOf(navArgument("bodyId") { type = NavType.StringType })
            ) { entry ->
                SolarBodyDetailScreen(
                    bodyId = entry.arguments?.getString("bodyId")!!,
                    onTakeQuizClicked = { planetId ->
                        navController.navigate("${SpaceScreen.Quiz.name}/$planetId")
                    }
                )
            }
            composable(SpaceScreen.Exoplanets.name) {
                ExploreScreen(
                    onExoplanetClicked = { encodedName ->
                        navController.navigate("${SpaceScreen.ExoplanetDetail.name}/$encodedName")
                    }
                )
            }
            composable(
                route = "${SpaceScreen.ExoplanetDetail.name}/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { entry ->
                ExoplanetDetailScreen(encodedName = entry.arguments?.getString("name")!!)
            }
            composable(SpaceScreen.Favorites.name) {
                FavoritesScreen(
                    onFavoriteClicked = { encodedName ->
                        navController.navigate("${SpaceScreen.ExoplanetDetail.name}/$encodedName")
                    }
                )
            }
            composable(SpaceScreen.Lessons.name) {
                LessonsScreen(
                    onLessonClicked = { id ->
                        navController.navigate("${SpaceScreen.LessonDetail.name}/$id")
                    }
                )
            }
            composable(
                route = "${SpaceScreen.LessonDetail.name}/{lessonId}",
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
            ) { entry ->
                LessonDetailScreen(
                    lessonId = entry.arguments?.getString("lessonId")!!,
                    onTakeQuizClicked = { planetId ->
                        navController.navigate("${SpaceScreen.Quiz.name}/$planetId")
                    }
                )
            }
            composable(SpaceScreen.Quiz.name) {
                PlanetQuizListScreen(
                    onPlanetQuizClicked = { id ->
                        navController.navigate("${SpaceScreen.Quiz.name}/$id")
                    },
                    onExoplanetBlitzClicked = {
                        navController.navigate(SpaceScreen.ExoplanetQuiz.name)
                    }
                )
            }
            composable(
                route = "${SpaceScreen.Quiz.name}/{planetId}",
                arguments = listOf(navArgument("planetId") { type = NavType.StringType })
            ) { entry ->
                QuizScreen(planetId = entry.arguments?.getString("planetId")!!)
            }
            composable(SpaceScreen.ExoplanetQuiz.name) {
                ExoplanetQuizScreen()
            }
        }
    }
}