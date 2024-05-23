package su.afk.quizfirebase

data class QuizModel(
    var id: String = "",
    var title: String = "",
    var descriptor: String = "",
    var time: String = "",
    var questionList: List<QuestionModel> = listOf()
)
//{
//    constructor() : this("", "", "", "", emptyList())
//}

data class QuestionModel(
    var question: String = "",
    var variants: List<String> = listOf(),
    var correct: String = ""
)
//{
//    constructor() : this("", emptyList(), "")
//}
