package com.example.a3_education_app.ui.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.ExoplanetRepository
import com.example.a3_education_app.data.UserPreferencesRepository
import com.example.a3_education_app.model.QuizQuestion
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ExoplanetQuizLoadState {
    object Loading : ExoplanetQuizLoadState
    object Error : ExoplanetQuizLoadState
    object Ready : ExoplanetQuizLoadState
}

class ExoplanetQuizViewModel(
    private val exoplanetRepository: ExoplanetRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    var loadState: ExoplanetQuizLoadState by mutableStateOf(ExoplanetQuizLoadState.Loading)
        private set

    private var questions: List<QuizQuestion> = emptyList()

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    val highScore: StateFlow<Int> =
        userPreferencesRepository.highScoreFlow(QUIZ_ID)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    init {
        loadQuiz()
    }

    fun loadQuiz() {
        viewModelScope.launch {
            loadState = ExoplanetQuizLoadState.Loading
            try {
                val query =
                    "select+top+40+pl_name,hostname,disc_year,pl_bmasse,pl_rade,sy_dist+from+pscomppars+where+disc_year+is+not+null+and+hostname+is+not+null+order+by+disc_year+desc"
                val bodies = exoplanetRepository.getExoplanets(query)
                    .filter {
                        it.pl_name.isNotBlank() &&
                                it.disc_year != null &&
                                !it.hostname.isNullOrBlank()
                    }
                questions = buildQuestions(bodies).shuffled().take(5)
                _uiState.value = QuizUiState()
                loadState = if (questions.size >= 5) {
                    ExoplanetQuizLoadState.Ready
                } else {
                    ExoplanetQuizLoadState.Error
                }
            } catch (_: IOException) {
                loadState = ExoplanetQuizLoadState.Error
            } catch (_: HttpException) {
                loadState = ExoplanetQuizLoadState.Error
            }
        }
    }

    fun currentQuestion(): QuizQuestion = questions[_uiState.value.currentQuestionIndex]

    fun questionCount(): Int = questions.size

    fun selectAnswer(index: Int) {
        val state = _uiState.value
        if (state.selectedAnswerIndex != null || state.isGameOver) return
        val correct = index == currentQuestion().correctAnswerIndex
        _uiState.update {
            it.copy(
                selectedAnswerIndex = index,
                score = if (correct) it.score + 1 else it.score
            )
        }
    }

    fun nextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex >= questions.size) {
            val finalScore = _uiState.value.score
            _uiState.update { it.copy(isGameOver = true) }
            viewModelScope.launch {
                userPreferencesRepository.updateHighScoreIfBetter(QUIZ_ID, finalScore)
            }
        } else {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswerIndex = null
                )
            }
        }
    }

    fun resetQuiz() {
        loadQuiz()
    }

    private fun buildQuestions(bodies: List<Exoplanet>): List<QuizQuestion> {
        if (bodies.size < 4) return emptyList()
        val result = mutableListOf<QuizQuestion>()

        bodies.shuffled().take(15).forEach { target ->
            val year = target.disc_year ?: return@forEach
            val wrongYears = bodies.mapNotNull { it.disc_year }
                .filter { it != year }
                .distinct()
                .shuffled()
                .take(3)
            if (wrongYears.size < 3) return@forEach
            val options = (wrongYears + year).map { it.toString() }.shuffled()
            result += QuizQuestion(
                question = "In what year was ${target.pl_name} discovered?",
                options = options,
                correctAnswerIndex = options.indexOf(year.toString())
            )

            val host = target.hostname ?: return@forEach
            val wrongHosts = bodies.mapNotNull { it.hostname }
                .filter { it != host }
                .distinct()
                .shuffled()
                .take(3)
            if (wrongHosts.size < 3) return@forEach
            val hostOptions = (wrongHosts + host).shuffled()
            result += QuizQuestion(
                question = "Which star hosts ${target.pl_name}?",
                options = hostOptions,
                correctAnswerIndex = hostOptions.indexOf(host)
            )
        }
        return result
    }

    companion object {
        const val QUIZ_ID = "exoplanet_blitz"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                ExoplanetQuizViewModel(
                    exoplanetRepository = app.container.exoplanetRepository,
                    userPreferencesRepository = app.container.userPreferencesRepository
                )
            }
        }
    }
}