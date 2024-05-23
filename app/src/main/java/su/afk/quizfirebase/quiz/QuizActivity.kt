package su.afk.quizfirebase.quiz

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import su.afk.quizfirebase.models.QuestionModel
import su.afk.quizfirebase.R
import su.afk.quizfirebase.databinding.ActivityQuizBinding
import su.afk.quizfirebase.databinding.DialogScoreBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex = 0
    var selectedAnswer = ""
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadQuestions()
        startTimer()

        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            btnNextQuiz.setOnClickListener(this@QuizActivity)
        }
    }

    private fun loadQuestions() {
        selectedAnswer = ""
        if(currentQuestionIndex == questionModelList.size) {
            finishQuiz() //если вопросов больше нету
            return
        }

        binding.apply {
            tvQuizIndicator.text = "Вопрос ${currentQuestionIndex+1}/${questionModelList.size}"
            progressBar.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            tvQuizQuestion.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].variants[0]
            btn1.text = questionModelList[currentQuestionIndex].variants[1]
            btn2.text = questionModelList[currentQuestionIndex].variants[2]
            btn3.text = questionModelList[currentQuestionIndex].variants[3]
//            btnNextQuiz.setOnClickListener {
//                buttonClick(btn0)
//                buttonClick(btn1)
//                buttonClick(btn2)
//                buttonClick(btn3)
//            }
        }

    }

    private fun startTimer() {
        val totalTimeMillis = timer.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds/60
                val remainSeconds = seconds % 60
                binding.tvQuizTimeTimer.text = String.format("%02d:%02d", minutes, remainSeconds)
            }

            override fun onFinish() {
                // Finish the quiz
            }
        }.start()
    }


//    private fun buttonClick(buttonView: View){
//            btn0.text = questionModelList[currentQuestionIndex].variants[0]
//            btn1.text = questionModelList[currentQuestionIndex].variants[1]
//            btn2.text = questionModelList[currentQuestionIndex].variants[2]
//            btn3.text = questionModelList[currentQuestionIndex].variants[3]
//    }

//    private fun onClick(buttonView: View) {
//        object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//    }




    companion object {
        var questionModelList : List<QuestionModel> = listOf()
        var timer : String = ""
    }

    override fun onClick(view: View?) {

        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn1.setBackgroundColor(getColor(R.color.grey))
            btn2.setBackgroundColor(getColor(R.color.grey))
            btn3.setBackgroundColor(getColor(R.color.grey))
        }   // делаем все кнеопки серыми при клике

        val clickedButton = view as Button
        if(clickedButton.id == R.id.btnNextQuiz) {//нажата кнопка далее
            if(selectedAnswer.isEmpty()){ // если никакой вариант ответа не выбран
                Toast.makeText(applicationContext, "Выберите вариант ответа", Toast.LENGTH_LONG).show()
                return
            }

            if(selectedAnswer == questionModelList[currentQuestionIndex].correct) score++ // если ответ был верным
            currentQuestionIndex ++
            loadQuestions()
        } else {
            selectedAnswer = clickedButton.text.toString() // сохраняем выбранный ответ
            clickedButton.setBackgroundColor(getColor(R.color.orange)) //меняем цвет кнопки которую кликнули
        }
    }

    //диалоговое окно
    private fun finishQuiz() {
        val totalQuestion = questionModelList.size
        val percentage = (score.toFloat() / totalQuestion.toFloat() * 100).toInt()

        val dialogBinding = DialogScoreBinding.inflate(layoutInflater)
        dialogBinding.apply {
            progressbar.progress = percentage
            tvScoreProgressText.text = "$percentage %"

            if(percentage > 60) {
                tvScoreTitle.text = "Тест успешно пройден!"
                tvScoreTitle.setTextColor(Color.BLUE)
            } else {
                tvScoreTitle.text = "Тест не пройден :с"
                tvScoreTitle.setTextColor(Color.RED)
            }
            tvScoreSub.text = "$score из $totalQuestion вопросов было правильными"
            btnFinish.setOnClickListener {
                finish()
            }
        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }
}