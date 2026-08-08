package com.example.a3_education_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.a3_education_app.data.QuizQuestionDataSource
import com.example.a3_education_app.ui.quiz.QuizScreen
import com.example.a3_education_app.ui.theme.A3EducationAppTheme
import org.junit.Rule
import org.junit.Test

class QuizScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun quizScreen_displaysFirstQuestionAndOptions() {
        val firstQuestion = QuizQuestionDataSource.questions.first()

        composeTestRule.setContent {
            A3EducationAppTheme {
                QuizScreen()
            }
        }

        composeTestRule.onNodeWithText(firstQuestion.question).assertIsDisplayed()
        composeTestRule.onNodeWithText(firstQuestion.options[0]).assertIsDisplayed()
        composeTestRule.onNodeWithText("Score: 0").assertIsDisplayed()
    }

    @Test
    fun quizScreen_selectAnswer_showsNextButton() {
        val firstQuestion = QuizQuestionDataSource.questions.first()
        val correctOption = firstQuestion.options[firstQuestion.correctAnswerIndex]

        composeTestRule.setContent {
            A3EducationAppTheme {
                QuizScreen()
            }
        }

        composeTestRule.onNodeWithText(correctOption).performClick()
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
        composeTestRule.onNodeWithText("Score: 1").assertIsDisplayed()
    }
}