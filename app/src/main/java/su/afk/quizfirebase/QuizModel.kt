package su.afk.quizfirebase

data class QuizModel(
    val id: String,
    val title: String,
    val descriptor: String,
    val time: String
){
    constructor(): this("", "", "", "")
}

