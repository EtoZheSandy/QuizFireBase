package su.afk.quizfirebase.models

data class QuizModel(
    var id: String = "",
    var title: String = "",
    var descriptor: String = "",
    var time: String = "",
    var questionList: List<QuestionModel> = listOf()
)


data class QuestionModel(
    var question: String = "",
    var variants: List<String> = listOf(),
    var correct: String = ""
)

