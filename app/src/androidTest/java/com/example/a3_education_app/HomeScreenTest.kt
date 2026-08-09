package com.example.a3_education_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.a3_education_app.ui.home.HomeScreen
import com.example.a3_education_app.ui.theme.A3EducationAppTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysAllNavigationButtons() {
        composeTestRule.setContent {
            A3EducationAppTheme {
                HomeScreen(
                    onSolarSystemClicked = {},
                    onExoplanetsClicked = {},
                    onFavoritesClicked = {},
                    onLessonsClicked = {},
                    onQuizClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Solar System").assertIsDisplayed()
        composeTestRule.onNodeWithText("Exoplanets").assertIsDisplayed()
        composeTestRule.onNodeWithText("Favorites").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lessons").assertIsDisplayed()
        composeTestRule.onNodeWithText("Quiz").assertIsDisplayed()
    }
}