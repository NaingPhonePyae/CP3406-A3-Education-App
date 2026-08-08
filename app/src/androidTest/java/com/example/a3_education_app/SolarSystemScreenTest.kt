package com.example.a3_education_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.a3_education_app.ui.solar.SolarSystemScreen
import com.example.a3_education_app.ui.theme.A3EducationAppTheme
import org.junit.Rule
import org.junit.Test

class SolarSystemScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun solarSystemScreen_displaysEarth() {
        composeTestRule.setContent {
            A3EducationAppTheme {
                SolarSystemScreen(onBodyClicked = {})
            }
        }

        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mars").assertIsDisplayed()
    }
}