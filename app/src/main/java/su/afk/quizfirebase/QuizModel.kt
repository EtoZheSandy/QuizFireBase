package su.afk.quizfirebase

data class QuizModel(
    val id: String,
    val title: String,
    val descriptor: String,
    val time: String,
    val questionList: List<QuestionModel>
)

data class QuestionModel(
    val question: String,
    val variants: List<String>,
    val correct: String
)
